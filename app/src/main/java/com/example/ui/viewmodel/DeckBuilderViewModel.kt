package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.datasource.MetaDeckTemplate
import com.example.data.datasource.MetaDecksData
import com.example.data.datasource.TagForceCardDatabase
import com.example.data.local.TagForceDatabase
import com.example.data.local.entity.DeckEntity
import com.example.data.model.Archetype
import com.example.data.model.CardSubstitution
import com.example.data.model.CardType
import com.example.data.model.DeckCardItem
import com.example.data.model.DeckSection
import com.example.data.model.PlayerLevel
import com.example.data.model.TacticalRole
import com.example.data.model.TagForceCard
import com.example.data.repository.DeckRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DeckBuilderViewModel(application: Application) : AndroidViewModel(application) {

    private val database = TagForceDatabase.getDatabase(application)
    private val repository = DeckRepository(database.deckDao(), database.userCollectionDao())

    val allDecks: StateFlow<List<DeckEntity>> = repository.allDecks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _activeDeckId = MutableStateFlow<Long?>(null)
    val activeDeckId = _activeDeckId.asStateFlow()

    private val _activeDeck = MutableStateFlow<DeckEntity?>(null)
    val activeDeck = _activeDeck.asStateFlow()

    private val _deckCards = MutableStateFlow<List<DeckCardItem>>(emptyList())
    val deckCards = _deckCards.asStateFlow()

    private val _ownedCardIds = MutableStateFlow<Set<String>>(emptySet())
    val ownedCardIds = _ownedCardIds.asStateFlow()

    private val _playerLevel = MutableStateFlow(PlayerLevel.LEVEL_1_EARLY)
    val playerLevel = _playerLevel.asStateFlow()

    // Filters for Card Search
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _filterLevel = MutableStateFlow<PlayerLevel?>(null)
    val filterLevel = _filterLevel.asStateFlow()

    private val _filterCardType = MutableStateFlow<CardType?>(null)
    val filterCardType = _filterCardType.asStateFlow()

    private val _filterArchetype = MutableStateFlow<Archetype?>(null)
    val filterArchetype = _filterArchetype.asStateFlow()

    private val _filterRole = MutableStateFlow<TacticalRole?>(null)
    val filterRole = _filterRole.asStateFlow()

    // Dialog & Sheet States
    private val _selectedCardForSynergy = MutableStateFlow<TagForceCard?>(null)
    val selectedCardForSynergy = _selectedCardForSynergy.asStateFlow()

    private val _selectedCardForSubstitutions = MutableStateFlow<TagForceCard?>(null)
    val selectedCardForSubstitutions = _selectedCardForSubstitutions.asStateFlow()

    private val _showHandTestDialog = MutableStateFlow(false)
    val showHandTestDialog = _showHandTestDialog.asStateFlow()

    private val _activeSectionTab = MutableStateFlow(DeckSection.MAIN)
    val activeSectionTab = _activeSectionTab.asStateFlow()

    // Filtered Cards for Catalog
    val searchResults: StateFlow<List<TagForceCard>> = combine(
        _searchQuery,
        _filterLevel,
        _filterCardType,
        _filterArchetype,
        _filterRole
    ) { query, level, type, archetype, role ->
        TagForceCardDatabase.searchCards(
            query = query,
            playerLevelFilter = level,
            cardTypeFilter = type,
            archetypeFilter = archetype,
            tacticalRoleFilter = role
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TagForceCardDatabase.allCards)

    init {
        // Observe user profile to restore player level
        viewModelScope.launch {
            repository.userProfile.collect { profile ->
                if (profile != null) {
                    val lvl = when (profile.playerLevel) {
                        1 -> PlayerLevel.LEVEL_1_EARLY
                        2 -> PlayerLevel.LEVEL_2_MID
                        3 -> PlayerLevel.LEVEL_3_ENDGAME
                        else -> PlayerLevel.LEVEL_1_EARLY
                    }
                    _playerLevel.value = lvl
                }
            }
        }

        // Observe owned cards
        viewModelScope.launch {
            repository.ownedCards.collect { list ->
                _ownedCardIds.value = list.map { it.cardId }.toSet()
            }
        }

        // Initialize default Starter Deck if no decks exist
        viewModelScope.launch {
            val decks = repository.allDecks.firstOrNull() ?: emptyList()
            if (decks.isEmpty()) {
                val starterTemplate = MetaDecksData.metaDecks.first { it.id == "meta_starter_beatdown" }
                val defaultId = repository.createDeckFromMeta(starterTemplate)
                selectDeck(defaultId)
                // Also give initial level 1 cards
                repository.unlockAllCardsForLevel(PlayerLevel.LEVEL_1_EARLY)
            } else {
                selectDeck(decks.first().id)
            }
        }
    }

    fun selectDeck(deckId: Long) {
        _activeDeckId.value = deckId
        viewModelScope.launch {
            repository.allDecks.collect { list ->
                _activeDeck.value = list.find { it.id == deckId }
            }
        }
        viewModelScope.launch {
            repository.getDeckCards(deckId).collect { cards ->
                _deckCards.value = cards
            }
        }
    }

    fun createNewDeck(name: String, archetype: String = "GENERIC") {
        viewModelScope.launch {
            val newId = repository.saveDeck(
                deckId = null,
                name = name,
                description = "Deck personalizado para Tag Force 3",
                archetype = archetype,
                cards = emptyList()
            )
            selectDeck(newId)
        }
    }

    fun deleteDeck(deck: DeckEntity) {
        viewModelScope.launch {
            repository.deleteDeck(deck)
            val remaining = repository.allDecks.firstOrNull() ?: emptyList()
            if (remaining.isNotEmpty()) {
                selectDeck(remaining.first().id)
            } else {
                _activeDeckId.value = null
                _activeDeck.value = null
                _deckCards.value = emptyList()
            }
        }
    }

    fun addCardToDeck(card: TagForceCard, section: DeckSection? = null) {
        val currentCards = _deckCards.value.toMutableList()
        val targetSection = section ?: if (card.cardType in listOf(CardType.FUSION_MONSTER, CardType.SYNCHRO_MONSTER)) {
            DeckSection.EXTRA
        } else DeckSection.MAIN

        val existingIndex = currentCards.indexOfFirst { it.card.id == card.id && it.section == targetSection }
        val limit = card.banStatus.limit

        if (existingIndex >= 0) {
            val existing = currentCards[existingIndex]
            if (existing.count < limit) {
                currentCards[existingIndex] = existing.copy(count = existing.count + 1)
                persistCurrentDeck(currentCards)
            }
        } else {
            if (limit > 0) {
                currentCards.add(DeckCardItem(card = card, count = 1, section = targetSection))
                persistCurrentDeck(currentCards)
            }
        }
    }

    fun removeCardFromDeck(cardId: String, section: DeckSection) {
        val currentCards = _deckCards.value.toMutableList()
        val index = currentCards.indexOfFirst { it.card.id == cardId && it.section == section }
        if (index >= 0) {
            val existing = currentCards[index]
            if (existing.count > 1) {
                currentCards[index] = existing.copy(count = existing.count - 1)
            } else {
                currentCards.removeAt(index)
            }
            persistCurrentDeck(currentCards)
        }
    }

    fun replaceCard(oldCard: TagForceCard, newCard: TagForceCard) {
        val currentCards = _deckCards.value.toMutableList()
        val index = currentCards.indexOfFirst { it.card.id == oldCard.id }
        if (index >= 0) {
            val oldItem = currentCards[index]
            val section = if (newCard.cardType in listOf(CardType.FUSION_MONSTER, CardType.SYNCHRO_MONSTER)) {
                DeckSection.EXTRA
            } else DeckSection.MAIN

            currentCards.removeAt(index)
            val newExistingIndex = currentCards.indexOfFirst { it.card.id == newCard.id && it.section == section }
            if (newExistingIndex >= 0) {
                val existing = currentCards[newExistingIndex]
                currentCards[newExistingIndex] = existing.copy(
                    count = (existing.count + oldItem.count).coerceAtMost(newCard.banStatus.limit)
                )
            } else {
                currentCards.add(
                    DeckCardItem(
                        card = newCard,
                        count = oldItem.count.coerceAtMost(newCard.banStatus.limit),
                        section = section
                    )
                )
            }
            persistCurrentDeck(currentCards)
        }
    }

    fun autoReplaceMissingCardsInCurrentDeck() {
        val currentCards = _deckCards.value
        val owned = _ownedCardIds.value
        val level = _playerLevel.value

        currentCards.filter { it.card.id !in owned }.forEach { missingItem ->
            val subs = TagForceCardDatabase.findSubstitutionsFor(missingItem.card, level)
            val bestAvailable = subs.firstOrNull { it.isAvailableAtCurrentLevel } ?: subs.firstOrNull()
            if (bestAvailable != null) {
                replaceCard(missingItem.card, bestAvailable.replacementCard)
            }
        }
    }

    private fun persistCurrentDeck(cards: List<DeckCardItem>) {
        _deckCards.value = cards
        val deckId = _activeDeckId.value ?: return
        val deck = _activeDeck.value ?: return
        viewModelScope.launch {
            repository.saveDeck(
                deckId = deckId,
                name = deck.name,
                description = deck.description,
                archetype = deck.archetype,
                cards = cards
            )
        }
    }

    fun importMetaDeck(template: MetaDeckTemplate) {
        viewModelScope.launch {
            val newId = repository.createDeckFromMeta(template)
            selectDeck(newId)
        }
    }

    fun buildDeckAroundCard(card: TagForceCard) {
        viewModelScope.launch {
            val newId = repository.createDeckBasedOnCard(card)
            selectDeck(newId)
        }
    }

    fun setPlayerLevel(level: PlayerLevel) {
        _playerLevel.value = level
        viewModelScope.launch {
            repository.setPlayerLevel(level)
        }
    }

    fun unlockAllCardsForLevel(level: PlayerLevel) {
        viewModelScope.launch {
            repository.unlockAllCardsForLevel(level)
        }
    }

    fun toggleCardOwned(cardId: String) {
        val currentlyOwned = cardId in _ownedCardIds.value
        viewModelScope.launch {
            repository.toggleCardOwned(cardId, !currentlyOwned)
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setFilterLevel(level: PlayerLevel?) {
        _filterLevel.value = level
    }

    fun setFilterCardType(type: CardType?) {
        _filterCardType.value = type
    }

    fun setFilterArchetype(archetype: Archetype?) {
        _filterArchetype.value = archetype
    }

    fun setFilterRole(role: TacticalRole?) {
        _filterRole.value = role
    }

    fun openSynergyDialog(card: TagForceCard) {
        _selectedCardForSynergy.value = card
    }

    fun closeSynergyDialog() {
        _selectedCardForSynergy.value = null
    }

    fun openSubstitutionSheet(card: TagForceCard) {
        _selectedCardForSubstitutions.value = card
    }

    fun closeSubstitutionSheet() {
        _selectedCardForSubstitutions.value = null
    }

    fun setHandTestDialogVisible(visible: Boolean) {
        _showHandTestDialog.value = visible
    }

    fun setActiveSectionTab(section: DeckSection) {
        _activeSectionTab.value = section
    }
}
