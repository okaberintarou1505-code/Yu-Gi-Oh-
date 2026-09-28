package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.CardType
import com.example.data.model.DeckSection
import com.example.data.model.PlayerLevel
import com.example.ui.components.CardSubstitutionSheet
import com.example.ui.components.HandTestDialog
import com.example.ui.components.SynergyAnalysisDialog
import com.example.ui.components.TagForceCardView
import com.example.ui.theme.CardEffectColor
import com.example.ui.theme.CardSpellColor
import com.example.ui.theme.CardTrapColor
import com.example.ui.theme.MillenniumGold
import com.example.ui.theme.ObeliskBlue
import com.example.ui.theme.SliferRed
import com.example.ui.viewmodel.DeckBuilderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckBuilderScreen(
    viewModel: DeckBuilderViewModel,
    onNavigateToSearch: () -> Unit
) {
    val activeDeck by viewModel.activeDeck.collectAsStateWithLifecycle()
    val deckCards by viewModel.deckCards.collectAsStateWithLifecycle()
    val allDecks by viewModel.allDecks.collectAsStateWithLifecycle()
    val ownedCardIds by viewModel.ownedCardIds.collectAsStateWithLifecycle()
    val playerLevel by viewModel.playerLevel.collectAsStateWithLifecycle()

    val selectedCardForSynergy by viewModel.selectedCardForSynergy.collectAsStateWithLifecycle()
    val selectedCardForSubstitutions by viewModel.selectedCardForSubstitutions.collectAsStateWithLifecycle()
    val showHandTestDialog by viewModel.showHandTestDialog.collectAsStateWithLifecycle()

    var activeTab by remember { mutableIntStateOf(0) } // 0 = Main, 1 = Extra, 2 = Stats
    var showNewDeckDialog by remember { mutableStateOf(false) }
    var showSwitchDeckDialog by remember { mutableStateOf(false) }

    val mainDeckCards = deckCards.filter { it.section == DeckSection.MAIN }
    val extraDeckCards = deckCards.filter { it.section == DeckSection.EXTRA }

    val totalMainCount = mainDeckCards.sumOf { it.count }
    val totalExtraCount = extraDeckCards.sumOf { it.count }

    val missingCards = deckCards.filter { it.card.id !in ownedCardIds }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Deck Header Bar
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = activeDeck?.name ?: "Nenhum Deck Ativo",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Text(
                            text = "Arquétipo: ${activeDeck?.archetype ?: "Geral"}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MillenniumGold
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        IconButton(
                            onClick = { showSwitchDeckDialog = true },
                            modifier = Modifier.testTag("switch_deck_btn")
                        ) {
                            Icon(Icons.Default.FolderOpen, contentDescription = "Trocar Deck", tint = MillenniumGold)
                        }
                        IconButton(
                            onClick = { showNewDeckDialog = true },
                            modifier = Modifier.testTag("new_deck_btn")
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Criar Deck", tint = MillenniumGold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Deck Counters
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val mainStatusColor = if (totalMainCount in 40..60) Color(0xFF43A047) else SliferRed
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = mainStatusColor.copy(alpha = 0.15f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, mainStatusColor.copy(alpha = 0.5f)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Main Deck", style = MaterialTheme.typography.labelMedium)
                            Text(
                                text = "$totalMainCount / 40-60",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = mainStatusColor
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = ObeliskBlue.copy(alpha = 0.15f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, ObeliskBlue.copy(alpha = 0.5f)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Extra Deck", style = MaterialTheme.typography.labelMedium)
                            Text(
                                text = "$totalExtraCount / 15",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = ObeliskBlue
                            )
                        }
                    }
                }

                // Missing Cards Alert Banner with Auto-Replace Button
                if (missingCards.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = SliferRed.copy(alpha = 0.15f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SliferRed.copy(alpha = 0.5f)),
                        modifier = Modifier.fillMaxWidth().testTag("missing_cards_alert")
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Warning, contentDescription = null, tint = SliferRed, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "${missingCards.size} cartas faltam na sua coleção de ${playerLevel.subtitle}",
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                    color = SliferRed
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Button(
                                onClick = { viewModel.autoReplaceMissingCardsInCurrentDeck() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = SliferRed,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth().height(36.dp).testTag("auto_replace_btn")
                            ) {
                                Icon(Icons.Default.SwapHoriz, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Substituir Faltantes Automaticamente", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Secondary Tabs: Main Deck, Extra Deck, Estatísticas
        SecondaryTabRow(
            selectedTabIndex = activeTab,
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Tab(
                selected = activeTab == 0,
                onClick = { activeTab = 0 },
                text = { Text("Main ($totalMainCount)") },
                modifier = Modifier.testTag("tab_main_deck")
            )
            Tab(
                selected = activeTab == 1,
                onClick = { activeTab = 1 },
                text = { Text("Extra ($totalExtraCount)") },
                modifier = Modifier.testTag("tab_extra_deck")
            )
            Tab(
                selected = activeTab == 2,
                onClick = { activeTab = 2 },
                text = { Text("Análise & Stats") },
                modifier = Modifier.testTag("tab_stats")
            )
        }

        // Bottom Action Bar inside screen
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ElevatedButton(
                onClick = { viewModel.setHandTestDialogVisible(true) },
                modifier = Modifier.weight(1f).testTag("hand_simulator_btn"),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MillenniumGold,
                    contentColor = Color(0xFF1E1400)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Casino, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Testar Mão", fontWeight = FontWeight.Bold)
            }

            OutlinedButton(
                onClick = onNavigateToSearch,
                modifier = Modifier.weight(1f).testTag("browse_cards_btn"),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Adicionar Cartas")
            }
        }

        // Main Tab Content
        when (activeTab) {
            0 -> {
                // Main Deck List
                if (mainDeckCards.isEmpty()) {
                    EmptyDeckState(onAddClick = onNavigateToSearch)
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(mainDeckCards, key = { it.card.id + it.section.name }) { item ->
                            TagForceCardView(
                                card = item.card,
                                deckCount = item.count,
                                isOwned = item.card.id in ownedCardIds,
                                onAddClick = { viewModel.addCardToDeck(item.card, DeckSection.MAIN) },
                                onRemoveClick = { viewModel.removeCardFromDeck(item.card.id, DeckSection.MAIN) },
                                onSynergyClick = { viewModel.openSynergyDialog(item.card) },
                                onSubstitutionsClick = { viewModel.openSubstitutionSheet(item.card) },
                                onToggleOwned = { viewModel.toggleCardOwned(item.card.id) }
                            )
                        }
                    }
                }
            }
            1 -> {
                // Extra Deck List
                if (extraDeckCards.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Nenhum Monstro de Fusão ou Sincro no Deck Adicional.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(onClick = onNavigateToSearch) {
                                Text("Buscar Fusões & Sincros")
                            }
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(extraDeckCards, key = { it.card.id + it.section.name }) { item ->
                            TagForceCardView(
                                card = item.card,
                                deckCount = item.count,
                                isOwned = item.card.id in ownedCardIds,
                                onAddClick = { viewModel.addCardToDeck(item.card, DeckSection.EXTRA) },
                                onRemoveClick = { viewModel.removeCardFromDeck(item.card.id, DeckSection.EXTRA) },
                                onSynergyClick = { viewModel.openSynergyDialog(item.card) },
                                onSubstitutionsClick = { viewModel.openSubstitutionSheet(item.card) },
                                onToggleOwned = { viewModel.toggleCardOwned(item.card.id) }
                            )
                        }
                    }
                }
            }
            2 -> {
                // Statistics & Synergy Overview
                DeckStatisticsTab(deckCards = deckCards, totalMain = totalMainCount)
            }
        }
    }

    // Modal dialogs
    if (selectedCardForSynergy != null) {
        SynergyAnalysisDialog(
            card = selectedCardForSynergy!!,
            onDismiss = { viewModel.closeSynergyDialog() },
            onAddPartnerToDeck = { partner -> viewModel.addCardToDeck(partner) },
            onBuildDeckAround = { card -> viewModel.buildDeckAroundCard(card) }
        )
    }

    if (selectedCardForSubstitutions != null) {
        CardSubstitutionSheet(
            targetCard = selectedCardForSubstitutions!!,
            currentPlayerLevel = playerLevel,
            onDismiss = { viewModel.closeSubstitutionSheet() },
            onReplaceCard = { old, new -> viewModel.replaceCard(old, new) }
        )
    }

    if (showHandTestDialog) {
        HandTestDialog(
            deckCards = deckCards,
            onDismiss = { viewModel.setHandTestDialogVisible(false) }
        )
    }

    // Create New Deck Dialog
    if (showNewDeckDialog) {
        var deckNameInput by remember { mutableStateOf("") }
        var archetypeInput by remember { mutableStateOf("HERO") }

        AlertDialog(
            onDismissRequest = { showNewDeckDialog = false },
            title = { Text("Criar Novo Deck") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = deckNameInput,
                        onValueChange = { deckNameInput = it },
                        label = { Text("Nome do Deck") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = archetypeInput,
                        onValueChange = { archetypeInput = it },
                        label = { Text("Arquétipo / Tema") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (deckNameInput.isNotBlank()) {
                            viewModel.createNewDeck(deckNameInput.trim(), archetypeInput.trim())
                            showNewDeckDialog = false
                        }
                    }
                ) {
                    Text("Criar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showNewDeckDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Switch Deck Dialog
    if (showSwitchDeckDialog) {
        AlertDialog(
            onDismissRequest = { showSwitchDeckDialog = false },
            title = { Text("Selecione um Deck") },
            text = {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(allDecks) { deck ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (deck.id == activeDeck?.id) MillenniumGold.copy(alpha = 0.2f)
                            else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.selectDeck(deck.id)
                                    showSwitchDeckDialog = false
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = deck.name,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    Text(
                                        text = "Arquétipo: ${deck.archetype}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                if (allDecks.size > 1) {
                                    IconButton(
                                        onClick = { viewModel.deleteDeck(deck) },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Deletar", tint = SliferRed)
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showSwitchDeckDialog = false }) {
                    Text("Fechar")
                }
            }
        )
    }
}

@Composable
fun EmptyDeckState(onAddClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.FolderOpen,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MillenniumGold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Seu Main Deck está vazio",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Adicione pelo menos 40 cartas para criar um deck competitivo no Tag Force 3.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onAddClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MillenniumGold,
                    contentColor = Color(0xFF1E1400)
                )
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(6.dp))
                Text("Explorar Banco de Cartas")
            }
        }
    }
}

@Composable
fun DeckStatisticsTab(
    deckCards: List<com.example.data.model.DeckCardItem>,
    totalMain: Int
) {
    val monsters = deckCards.filter { it.section == DeckSection.MAIN && it.card.level > 0 }.sumOf { it.count }
    val spells = deckCards.filter { it.section == DeckSection.MAIN && it.card.cardType == CardType.SPELL }.sumOf { it.count }
    val traps = deckCards.filter { it.section == DeckSection.MAIN && it.card.cardType == CardType.TRAP }.sumOf { it.count }

    val monstersLvl1to4 = deckCards.filter { it.section == DeckSection.MAIN && it.card.level in 1..4 }.sumOf { it.count }
    val monstersLvl5plus = deckCards.filter { it.section == DeckSection.MAIN && it.card.level >= 5 }.sumOf { it.count }

    val idealMonsterRatio = totalMain > 0 && monsters in (totalMain * 0.45).toInt()..(totalMain * 0.6).toInt()
    val idealSpellRatio = totalMain > 0 && spells in (totalMain * 0.25).toInt()..(totalMain * 0.4).toInt()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Distribuição do Deck ($totalMain cartas)",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    StatBar(label = "Monstros", count = monsters, total = totalMain, color = CardEffectColor)
                    Spacer(modifier = Modifier.height(10.dp))
                    StatBar(label = "Magias", count = spells, total = totalMain, color = CardSpellColor)
                    Spacer(modifier = Modifier.height(10.dp))
                    StatBar(label = "Armadilhas", count = traps, total = totalMain, color = CardTrapColor)
                }
            }
        }

        item {
            Text(
                text = "Curva de Invocação de Monstros",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    StatBar(
                        label = "Nível 1 a 4 (Invocação Normal)",
                        count = monstersLvl1to4,
                        total = totalMain,
                        color = Color(0xFF43A047)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    StatBar(
                        label = "Nível 5+ (Tributos / Chefes)",
                        count = monstersLvl5plus,
                        total = totalMain,
                        color = SliferRed
                    )
                }
            }
        }

        item {
            Text(
                text = "Dicas de Sinergia e Consistência (Meta 2008)",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, MillenniumGold.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = if (idealMonsterRatio) "✅ Proporção de monstros ideal para a era GX (~20 a 22 monstros)."
                        else "💡 Dica: O padrão clássico é 20 monstros, 12 a 14 magias e 6 a 8 armadilhas.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = if (monstersLvl1to4 >= 12) "✅ Boa base de Invocação Normal (12+ monstros Lv1-4 reduzem mãos mortas)."
                        else "⚠️ Atenção: Menos de 12 monstros de nível 4 pode causar mãos travadas no turno 1.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "⭐ Staples Recomendadas: Heavy Storm, Mystical Space Typhoon e Monster Reborn aceleram qualquer estratégia.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
fun StatBar(label: String, count: Int, total: Int, color: Color) {
    val progress = if (total > 0) count.toFloat() / total.toFloat() else 0f
    val percentage = if (total > 0) (progress * 100).toInt() else 0

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.labelMedium)
            Text(
                text = "$count ($percentage%)",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = color
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = color,
            trackColor = MaterialTheme.colorScheme.surface
        )
    }
}
