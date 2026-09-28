package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CardType
import com.example.data.model.DeckCardItem
import com.example.data.model.DeckSection
import com.example.data.model.TagForceCard
import com.example.ui.theme.MillenniumGold
import com.example.ui.theme.SliferRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandTestDialog(
    deckCards: List<DeckCardItem>,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    // Flat list of main deck cards according to their count
    val mainDeckList = remember(deckCards) {
        deckCards.filter { it.section == DeckSection.MAIN }
            .flatMap { item -> List(item.count) { item.card } }
    }

    var handSize by remember { mutableIntStateOf(5) }
    var seed by remember { mutableIntStateOf(0) }

    val drawnHand = remember(mainDeckList, seed, handSize) {
        if (mainDeckList.size >= 5) {
            mainDeckList.shuffled().take(handSize.coerceAtMost(mainDeckList.size))
        } else {
            mainDeckList
        }
    }

    // Evaluation
    val normalSummons = drawnHand.count { it.level in 1..4 && it.cardType in listOf(CardType.NORMAL_MONSTER, CardType.EFFECT_MONSTER) }
    val spells = drawnHand.count { it.cardType == CardType.SPELL }
    val traps = drawnHand.count { it.cardType == CardType.TRAP }
    val highLevel = drawnHand.count { it.level >= 5 }

    val verdict = when {
        mainDeckList.size < 40 -> "Aviso: O Main Deck tem menos de 40 cartas (${mainDeckList.size}). Complete 40 para simulação fiel."
        normalSummons == 0 -> "⚠️ Risco de 'Brick': Sem monstro de Nível 4 ou menor para invocação normal no turno 1."
        normalSummons >= 1 && (spells >= 1 || traps >= 1) -> "✅ Mão Excelente: Invocação normal garantida com suporte de magia/armadilha!"
        else -> "⚖️ Mão Jogável: Boa distribuição para iniciar o duelo."
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.testTag("hand_test_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 32.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MillenniumGold.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Casino,
                            contentDescription = null,
                            tint = MillenniumGold,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Simulador de Mão Inicial",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Duelo Tag Force 3 (${drawnHand.size} cartas)",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Fechar")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Evaluation banner
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = if (normalSummons > 0) MaterialTheme.colorScheme.surfaceVariant else SliferRed.copy(alpha = 0.2f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = verdict,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(text = "Monstros Lv1-4: $normalSummons", style = MaterialTheme.typography.labelSmall)
                        Text(text = "Tributos: $highLevel", style = MaterialTheme.typography.labelSmall)
                        Text(text = "Magias: $spells", style = MaterialTheme.typography.labelSmall)
                        Text(text = "Armadilhas: $traps", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            LazyColumn(
                modifier = Modifier.weight(1f, fill = false),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(drawnHand) { card ->
                    val frameColor = getCardFrameColor(card.cardType)
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, frameColor.copy(alpha = 0.6f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = card.name,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = if (card.level > 0) "⭐ ${card.level} • ${card.monsterType.displayName} • ATK/${card.attack ?: 0}"
                                    else card.cardType.displayName,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = frameColor.copy(alpha = 0.2f)
                            ) {
                                Text(
                                    text = card.tacticalRole.displayName,
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                    color = frameColor,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        handSize = 5
                        seed += 1
                    },
                    modifier = Modifier.weight(1f).testTag("mulligan_btn"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MillenniumGold,
                        contentColor = Color(0xFF1E1400)
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Redraw (5)", fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = {
                        if (handSize < mainDeckList.size) handSize += 1
                    },
                    modifier = Modifier.weight(1f).testTag("draw_turn_btn"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("+ Comprar Turno 2")
                }
            }
        }
    }
}
