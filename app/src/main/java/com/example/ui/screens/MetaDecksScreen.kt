package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.datasource.MetaDeckTemplate
import com.example.data.datasource.MetaDecksData
import com.example.data.datasource.TagForceCardDatabase
import com.example.data.model.PlayerLevel
import com.example.ui.components.getPlayerLevelBadgeColor
import com.example.ui.theme.MillenniumGold
import com.example.ui.theme.ObeliskBlue
import com.example.ui.theme.SliferRed
import com.example.ui.viewmodel.DeckBuilderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetaDecksScreen(
    viewModel: DeckBuilderViewModel,
    onDeckImported: () -> Unit
) {
    val playerLevel by viewModel.playerLevel.collectAsStateWithLifecycle()
    val ownedCardIds by viewModel.ownedCardIds.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Banner
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MillenniumGold.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = MillenniumGold,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Decks Meta da Época (2008)",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Yu-Gi-Oh! GX Tag Force 3 e Campeonatos Mundiais",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Meta Decks List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(MetaDecksData.metaDecks, key = { it.id }) { template ->
                MetaDeckCardItem(
                    template = template,
                    playerLevel = playerLevel,
                    ownedCardIds = ownedCardIds,
                    onImportClick = {
                        viewModel.importMetaDeck(template)
                        onDeckImported()
                    }
                )
            }
        }
    }
}

@Composable
fun MetaDeckCardItem(
    template: MetaDeckTemplate,
    playerLevel: PlayerLevel,
    ownedCardIds: Set<String>,
    onImportClick: () -> Unit
) {
    val totalMain = template.mainCards.sumOf { it.second }
    val totalExtra = template.extraCards.sumOf { it.second }
    val isLevelSufficient = playerLevel.levelNumber >= template.requiredLevel.levelNumber

    val missingCards = template.mainCards.count { (cardId, _) -> cardId !in ownedCardIds }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .border(
                1.dp,
                if (template.tier.contains("Tier 0")) MillenniumGold.copy(alpha = 0.8f)
                else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                RoundedCornerShape(14.dp)
            )
            .testTag("meta_deck_card_${template.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Name + Tier Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = template.name,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Main: $totalMain cartas" + if (totalExtra > 0) " • Extra: $totalExtra cartas" else "",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (template.tier.contains("Tier 0")) MillenniumGold.copy(alpha = 0.2f)
                    else if (template.tier.contains("Início")) Color(0xFF43A047).copy(alpha = 0.2f)
                    else SliferRed.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = template.tier,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = if (template.tier.contains("Tier 0")) MillenniumGold
                        else if (template.tier.contains("Início")) Color(0xFF43A047)
                        else SliferRed,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Player Level Required Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = getPlayerLevelBadgeColor(template.requiredLevel).copy(alpha = 0.15f)
                ) {
                    Text(
                        text = "Recomendado: ${template.requiredLevel.title}",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = getPlayerLevelBadgeColor(template.requiredLevel),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                if (missingCards > 0) {
                    Text(
                        text = "$missingCards cartas faltando",
                        style = MaterialTheme.typography.labelSmall,
                        color = SliferRed
                    )
                } else {
                    Text(
                        text = "Todas as cartas desbloqueadas!",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF43A047)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = template.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Strategy Summary Box
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Estratégia do Deck:",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = template.strategySummary,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Key Cards Chips
            Text(
                text = "Cartas Chave:",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(template.keyCardIds) { cardId ->
                    val card = TagForceCardDatabase.getCardById(cardId)
                    if (card != null) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.padding(vertical = 2.dp)
                        ) {
                            Text(
                                text = "🎴 ${card.name}",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Beginner Tip Box
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MillenniumGold.copy(alpha = 0.1f),
                border = androidx.compose.foundation.BorderStroke(1.dp, MillenniumGold.copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = MillenniumGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = template.beginnerSubstitutionsTip,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Import Button
            Button(
                onClick = onImportClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (template.tier.contains("Tier 0")) MillenniumGold else MaterialTheme.colorScheme.primary,
                    contentColor = if (template.tier.contains("Tier 0")) Color(0xFF1E1400) else Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .testTag("import_meta_${template.id}")
            ) {
                Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Copiar para Meus Decks",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
