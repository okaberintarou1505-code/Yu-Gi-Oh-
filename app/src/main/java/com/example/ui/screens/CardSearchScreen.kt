package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.CardType
import com.example.data.model.PlayerLevel
import com.example.data.model.TacticalRole
import com.example.ui.components.CardSubstitutionSheet
import com.example.ui.components.SynergyAnalysisDialog
import com.example.ui.components.TagForceCardView
import com.example.ui.theme.MillenniumGold
import com.example.ui.theme.ObeliskBlue
import com.example.ui.theme.RaYellow
import com.example.ui.theme.SliferRed
import com.example.ui.viewmodel.DeckBuilderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSearchScreen(
    viewModel: DeckBuilderViewModel
) {
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val filterLevel by viewModel.filterLevel.collectAsStateWithLifecycle()
    val filterCardType by viewModel.filterCardType.collectAsStateWithLifecycle()
    val filterRole by viewModel.filterRole.collectAsStateWithLifecycle()
    val ownedCardIds by viewModel.ownedCardIds.collectAsStateWithLifecycle()
    val playerLevel by viewModel.playerLevel.collectAsStateWithLifecycle()

    val selectedCardForSynergy by viewModel.selectedCardForSynergy.collectAsStateWithLifecycle()
    val selectedCardForSubstitutions by viewModel.selectedCardForSubstitutions.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Search Bar Top Section
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.setSearchQuery(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_text_input"),
                    placeholder = { Text("Buscar carta por nome, efeito ou booster pack...") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Pesquisar", tint = MillenniumGold)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "Limpar")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = MillenniumGold,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Progression Level Chips (Início / Meio / Fim de Jogo)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = filterLevel == null,
                        onClick = { viewModel.setFilterLevel(null) },
                        label = { Text("Todos os Níveis") },
                        modifier = Modifier.testTag("filter_level_all")
                    )

                    FilterChip(
                        selected = filterLevel == PlayerLevel.LEVEL_1_EARLY,
                        onClick = {
                            viewModel.setFilterLevel(
                                if (filterLevel == PlayerLevel.LEVEL_1_EARLY) null else PlayerLevel.LEVEL_1_EARLY
                            )
                        },
                        label = { Text("Nível 1: Início (Slifer Red)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = SliferRed.copy(alpha = 0.2f),
                            selectedLabelColor = SliferRed
                        ),
                        modifier = Modifier.testTag("filter_level_1")
                    )

                    FilterChip(
                        selected = filterLevel == PlayerLevel.LEVEL_2_MID,
                        onClick = {
                            viewModel.setFilterLevel(
                                if (filterLevel == PlayerLevel.LEVEL_2_MID) null else PlayerLevel.LEVEL_2_MID
                            )
                        },
                        label = { Text("Nível 2: Meio (Ra Yellow)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = RaYellow.copy(alpha = 0.2f),
                            selectedLabelColor = RaYellow
                        ),
                        modifier = Modifier.testTag("filter_level_2")
                    )

                    FilterChip(
                        selected = filterLevel == PlayerLevel.LEVEL_3_ENDGAME,
                        onClick = {
                            viewModel.setFilterLevel(
                                if (filterLevel == PlayerLevel.LEVEL_3_ENDGAME) null else PlayerLevel.LEVEL_3_ENDGAME
                            )
                        },
                        label = { Text("Nível 3: Meta (Obelisk Blue)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ObeliskBlue.copy(alpha = 0.2f),
                            selectedLabelColor = ObeliskBlue
                        ),
                        modifier = Modifier.testTag("filter_level_3")
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Card Type Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = filterCardType == null,
                        onClick = { viewModel.setFilterCardType(null) },
                        label = { Text("Todos os Tipos") }
                    )

                    FilterChip(
                        selected = filterCardType == CardType.NORMAL_MONSTER,
                        onClick = {
                            viewModel.setFilterCardType(
                                if (filterCardType == CardType.NORMAL_MONSTER) null else CardType.NORMAL_MONSTER
                            )
                        },
                        label = { Text("Normal") }
                    )

                    FilterChip(
                        selected = filterCardType == CardType.EFFECT_MONSTER,
                        onClick = {
                            viewModel.setFilterCardType(
                                if (filterCardType == CardType.EFFECT_MONSTER) null else CardType.EFFECT_MONSTER
                            )
                        },
                        label = { Text("Efeito") }
                    )

                    FilterChip(
                        selected = filterCardType == CardType.FUSION_MONSTER,
                        onClick = {
                            viewModel.setFilterCardType(
                                if (filterCardType == CardType.FUSION_MONSTER) null else CardType.FUSION_MONSTER
                            )
                        },
                        label = { Text("Fusão") }
                    )

                    FilterChip(
                        selected = filterCardType == CardType.SYNCHRO_MONSTER,
                        onClick = {
                            viewModel.setFilterCardType(
                                if (filterCardType == CardType.SYNCHRO_MONSTER) null else CardType.SYNCHRO_MONSTER
                            )
                        },
                        label = { Text("Sincro") }
                    )

                    FilterChip(
                        selected = filterCardType == CardType.SPELL,
                        onClick = {
                            viewModel.setFilterCardType(
                                if (filterCardType == CardType.SPELL) null else CardType.SPELL
                            )
                        },
                        label = { Text("Magia") }
                    )

                    FilterChip(
                        selected = filterCardType == CardType.TRAP,
                        onClick = {
                            viewModel.setFilterCardType(
                                if (filterCardType == CardType.TRAP) null else CardType.TRAP
                            )
                        },
                        label = { Text("Armadilha") }
                    )
                }
            }
        }

        // Search Results Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${searchResults.size} cartas encontradas",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "Seu Nível: ${playerLevel.subtitle}",
                style = MaterialTheme.typography.labelSmall,
                color = MillenniumGold
            )
        }

        // Cards List
        if (searchResults.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nenhuma carta encontrada com esses filtros.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(searchResults, key = { it.id }) { card ->
                    TagForceCardView(
                        card = card,
                        isOwned = card.id in ownedCardIds,
                        onAddClick = { viewModel.addCardToDeck(card) },
                        onSynergyClick = { viewModel.openSynergyDialog(card) },
                        onSubstitutionsClick = { viewModel.openSubstitutionSheet(card) },
                        onToggleOwned = { viewModel.toggleCardOwned(card.id) }
                    )
                }
            }
        }
    }

    // Modal Dialogs
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
}
