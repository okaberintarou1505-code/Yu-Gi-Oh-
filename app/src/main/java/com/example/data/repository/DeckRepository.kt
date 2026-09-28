package com.example.data.repository

import com.example.data.datasource.MetaDeckTemplate
import com.example.data.datasource.MetaDecksData
import com.example.data.datasource.TagForceCardDatabase
import com.example.data.local.dao.DeckDao
import com.example.data.local.dao.UserCollectionDao
import com.example.data.local.entity.DeckCardEntity
import com.example.data.local.entity.DeckEntity
import com.example.data.local.entity.UserCardEntity
import com.example.data.local.entity.UserProfileEntity
import com.example.data.model.CardSubstitution
import com.example.data.model.DeckCardItem
import com.example.data.model.DeckSection
import com.example.data.model.PlayerLevel
import com.example.data.model.TagForceCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class DeckRepository(
    private val deckDao: DeckDao,
    private val collectionDao: UserCollectionDao
) {
    val allDecks: Flow<List<DeckEntity>> = deckDao.getAllDecks()
    val userProfile: Flow<UserProfileEntity?> = collectionDao.getUserProfile()
    val ownedCards: Flow<List<UserCardEntity>> = collectionDao.getAllOwnedCards()

    fun getDeckCards(deckId: Long): Flow<List<DeckCardItem>> {
        return deckDao.getDeckCards(deckId).map { entities ->
            entities.mapNotNull { entity ->
                val card = TagForceCardDatabase.getCardById(entity.cardId)
                if (card != null) {
                    val section = try {
                        DeckSection.valueOf(entity.section)
                    } catch (_: Exception) {
                        DeckSection.MAIN
                    }
                    DeckCardItem(card = card, count = entity.count, section = section)
                } else null
            }
        }
    }

    suspend fun saveDeck(
        deckId: Long?,
        name: String,
        description: String,
        archetype: String,
        cards: List<DeckCardItem>
    ): Long {
        val entity = DeckEntity(
            id = deckId ?: 0,
            name = name,
            description = description,
            archetype = archetype,
            updatedAt = System.currentTimeMillis()
        )
        val savedId = if (deckId == null || deckId == 0L) {
            deckDao.insertDeck(entity)
        } else {
            deckDao.updateDeck(entity)
            deckId
        }

        deckDao.clearDeckCards(savedId)
        val cardEntities = cards.map {
            DeckCardEntity(
                deckId = savedId,
                cardId = it.card.id,
                count = it.count,
                section = it.section.name
            )
        }
        deckDao.insertDeckCards(cardEntities)
        return savedId
    }

    suspend fun deleteDeck(deck: DeckEntity) {
        deckDao.deleteDeck(deck)
    }

    suspend fun setPlayerLevel(level: PlayerLevel) {
        val current = collectionDao.getUserProfile().firstOrNull() ?: UserProfileEntity()
        collectionDao.saveUserProfile(current.copy(playerLevel = level.levelNumber))
    }

    suspend fun toggleCardOwned(cardId: String, isOwned: Boolean) {
        if (isOwned) {
            collectionDao.insertOrUpdateOwned(UserCardEntity(cardId = cardId, ownedCount = 3))
        } else {
            collectionDao.deleteOwnedCard(cardId)
        }
    }

    suspend fun unlockAllCardsForLevel(level: PlayerLevel) {
        val cardsToUnlock = TagForceCardDatabase.allCards
            .filter { it.requiredPlayerLevel.levelNumber <= level.levelNumber }
            .map { UserCardEntity(cardId = it.id, ownedCount = 3) }
        collectionDao.insertBatchOwned(cardsToUnlock)
    }

    suspend fun createDeckFromMeta(template: MetaDeckTemplate): Long {
        val deckEntity = DeckEntity(
            name = template.name,
            description = template.description,
            archetype = template.archetype,
            updatedAt = System.currentTimeMillis()
        )
        val deckId = deckDao.insertDeck(deckEntity)

        val mainEntities = template.mainCards.mapNotNull { (cardId, count) ->
            if (TagForceCardDatabase.getCardById(cardId) != null) {
                DeckCardEntity(
                    deckId = deckId,
                    cardId = cardId,
                    count = count,
                    section = DeckSection.MAIN.name
                )
            } else null
        }

        val extraEntities = template.extraCards.mapNotNull { (cardId, count) ->
            if (TagForceCardDatabase.getCardById(cardId) != null) {
                DeckCardEntity(
                    deckId = deckId,
                    cardId = cardId,
                    count = count,
                    section = DeckSection.EXTRA.name
                )
            } else null
        }

        deckDao.insertDeckCards(mainEntities + extraEntities)
        return deckId
    }

    /**
     * Constrói automaticamente um deck equilibrado com 40 cartas baseado em uma carta escolhida,
     * respeitando as staples e os pacotes da era Tag Force 3.
     */
    suspend fun createDeckBasedOnCard(card: TagForceCard): Long {
        val report = TagForceCardDatabase.analyzeSynergyForCard(card)
        val deckEntity = DeckEntity(
            name = "Deck: ${card.name}",
            description = "Estratégia sinérgica construída ao redor de ${card.name}. ${report.recommendedDeckStrategy.take(80)}...",
            archetype = card.archetype.name,
            updatedAt = System.currentTimeMillis()
        )
        val deckId = deckDao.insertDeck(deckEntity)

        val cardItems = mutableListOf<DeckCardEntity>()
        // Adiciona a carta alvo com quantidade legal
        val targetCount = when (card.banStatus.limit) {
            1 -> 1
            2 -> 2
            else -> 3
        }
        val targetSection = if (card.cardType in listOf(com.example.data.model.CardType.FUSION_MONSTER, com.example.data.model.CardType.SYNCHRO_MONSTER)) {
            DeckSection.EXTRA
        } else DeckSection.MAIN

        cardItems.add(DeckCardEntity(deckId = deckId, cardId = card.id, count = targetCount, section = targetSection.name))

        // Adiciona os melhores parceiros de sinergia
        var currentMainCount = if (targetSection == DeckSection.MAIN) targetCount else 0
        for (partner in report.bestPartners) {
            if (currentMainCount >= 40) break
            val section = if (partner.cardType in listOf(com.example.data.model.CardType.FUSION_MONSTER, com.example.data.model.CardType.SYNCHRO_MONSTER)) {
                DeckSection.EXTRA
            } else DeckSection.MAIN

            val count = partner.banStatus.limit.coerceAtMost(if (partner.tacticalRole in listOf(com.example.data.model.TacticalRole.SEARCHER, com.example.data.model.TacticalRole.DRAW_ENGINE)) 3 else 2)
            cardItems.add(DeckCardEntity(deckId = deckId, cardId = partner.id, count = count, section = section.name))
            if (section == DeckSection.MAIN) {
                currentMainCount += count
            }
        }

        // Se faltar cartas para o mínimo de 40 no Main Deck, completa com staples essenciais
        val staples = listOf("c_mystical_space_typhoon", "c_heavy_storm", "c_monster_reborn", "c_smashing_ground", "c_sakuretsu_armor", "c_mirror_force", "c_torrential_tribute", "c_bottomless_trap_hole", "c_solemn_judgment", "c_vorse_raider")
        for (stapleId in staples) {
            if (currentMainCount >= 40) break
            if (cardItems.none { it.cardId == stapleId }) {
                cardItems.add(DeckCardEntity(deckId = deckId, cardId = stapleId, count = 1, section = DeckSection.MAIN.name))
                currentMainCount += 1
            }
        }

        deckDao.insertDeckCards(cardItems)
        return deckId
    }

    /**
     * Substitui cartas faltantes de um deck por alternativas disponíveis no nível de progressão do usuário.
     */
    fun getSubstitutionsForMissingCards(
        deckCards: List<DeckCardItem>,
        ownedCardIds: Set<String>,
        playerLevel: PlayerLevel
    ): Map<String, List<CardSubstitution>> {
        val missing = deckCards.filter { it.card.id !in ownedCardIds }
        return missing.associate { item ->
            item.card.id to TagForceCardDatabase.findSubstitutionsFor(item.card, playerLevel)
        }
    }
}
