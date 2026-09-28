package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.BanStatus
import com.example.data.model.CardAttribute
import com.example.data.model.CardType
import com.example.data.model.PlayerLevel
import com.example.data.model.TagForceCard
import com.example.ui.theme.BanForbiddenColor
import com.example.ui.theme.BanLimitedColor
import com.example.ui.theme.BanSemiLimitedColor
import com.example.ui.theme.CardEffectColor
import com.example.ui.theme.CardFusionColor
import com.example.ui.theme.CardNormalColor
import com.example.ui.theme.CardSpellColor
import com.example.ui.theme.CardSynchroColor
import com.example.ui.theme.CardTrapColor
import com.example.ui.theme.MillenniumGold
import com.example.ui.theme.ObeliskBlue
import com.example.ui.theme.RaYellow
import com.example.ui.theme.SliferRed

fun getCardFrameColor(type: CardType): Color {
    return when (type) {
        CardType.NORMAL_MONSTER -> CardNormalColor
        CardType.EFFECT_MONSTER -> CardEffectColor
        CardType.FUSION_MONSTER -> CardFusionColor
        CardType.SYNCHRO_MONSTER -> CardSynchroColor
        CardType.SPELL -> CardSpellColor
        CardType.TRAP -> CardTrapColor
    }
}

fun getAttributeColor(attribute: CardAttribute): Color {
    return when (attribute) {
        CardAttribute.DARK -> Color(0xFF6A1B9A)
        CardAttribute.LIGHT -> Color(0xFFF9A825)
        CardAttribute.EARTH -> Color(0xFF8D6E63)
        CardAttribute.FIRE -> Color(0xFFD84315)
        CardAttribute.WATER -> Color(0xFF1E88E5)
        CardAttribute.WIND -> Color(0xFF43A047)
        CardAttribute.DIVINE -> Color(0xFFFFD700)
        CardAttribute.SPELL -> CardSpellColor
        CardAttribute.TRAP -> CardTrapColor
    }
}

fun getPlayerLevelBadgeColor(level: PlayerLevel): Color {
    return when (level) {
        PlayerLevel.LEVEL_1_EARLY -> SliferRed
        PlayerLevel.LEVEL_2_MID -> RaYellow
        PlayerLevel.LEVEL_3_ENDGAME -> ObeliskBlue
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagForceCardView(
    card: TagForceCard,
    modifier: Modifier = Modifier,
    isOwned: Boolean = true,
    deckCount: Int? = null,
    onAddClick: (() -> Unit)? = null,
    onRemoveClick: (() -> Unit)? = null,
    onSynergyClick: (() -> Unit)? = null,
    onSubstitutionsClick: (() -> Unit)? = null,
    onToggleOwned: (() -> Unit)? = null,
    onBuildDeckAround: (() -> Unit)? = null,
    compact: Boolean = false
) {
    val frameColor = getCardFrameColor(card.cardType)
    val attrColor = getAttributeColor(card.attribute)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.5.dp, frameColor.copy(alpha = 0.8f), RoundedCornerShape(12.dp))
            .testTag("card_item_${card.id}"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Header: Card Name, Attribute Symbol, Ban Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    // Frame indicator pip
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(CircleShape)
                            .background(frameColor)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = card.name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Attribute badge
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = attrColor.copy(alpha = 0.2f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, attrColor),
                    modifier = Modifier.padding(start = 6.dp)
                ) {
                    Text(
                        text = "${card.attribute.symbol} ${card.attribute.displayName}",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = attrColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Sub-header: Monster Stars / Type / Stats or Spell/Trap info
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (card.level > 0) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "⭐".repeat(card.level),
                            style = MaterialTheme.typography.labelSmall,
                            letterSpacing = (-2).sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "[${card.monsterType.displayName} / ${card.cardType.displayName}]",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    Text(
                        text = "[${card.cardType.displayName}]",
                        style = MaterialTheme.typography.labelMedium,
                        color = frameColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // Banlist indicator
                if (card.banStatus != BanStatus.UNLIMITED) {
                    val banColor = when (card.banStatus) {
                        BanStatus.FORBIDDEN -> BanForbiddenColor
                        BanStatus.LIMITED -> BanLimitedColor
                        BanStatus.SEMI_LIMITED -> BanSemiLimitedColor
                        else -> Color.Gray
                    }
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = banColor.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = card.banStatus.displayName,
                            color = banColor,
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                        )
                    }
                }
            }

            if (!compact) {
                Spacer(modifier = Modifier.height(6.dp))
                // Description Box
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = card.description,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp, lineHeight = 16.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(8.dp),
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // ATK / DEF / Pack and DP info
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (card.attack != null) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "ATK/${card.attack}",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.labelMedium,
                            color = SliferRed
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "DEF/${card.defense ?: 0}",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.labelMedium,
                            color = ObeliskBlue
                        )
                    }
                } else {
                    Text(
                        text = card.tacticalRole.displayName,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // Pack info & DP cost
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = getPlayerLevelBadgeColor(card.requiredPlayerLevel).copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = "Pack [${card.packCode}] ${card.packName} • ${card.packDpCost} DP",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Actions Row: Synergy, Substitutes, Owned Check, Add/Remove
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left chips: Sinergia & Substitutos
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    if (onSynergyClick != null) {
                        AssistChip(
                            onClick = onSynergyClick,
                            label = { Text("Sinergia", fontSize = 11.sp) },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.AutoAwesome,
                                    contentDescription = "Sinergia",
                                    tint = MillenniumGold,
                                    modifier = Modifier.size(14.dp)
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            ),
                            modifier = Modifier.height(30.dp).testTag("synergy_btn_${card.id}")
                        )
                    }

                    if (onSubstitutionsClick != null) {
                        AssistChip(
                            onClick = onSubstitutionsClick,
                            label = { Text("Substitutos", fontSize = 11.sp) },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.SwapHoriz,
                                    contentDescription = "Substitutos",
                                    modifier = Modifier.size(14.dp)
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            ),
                            modifier = Modifier.height(30.dp).testTag("subs_btn_${card.id}")
                        )
                    }
                }

                // Right controls: Collection status and Deck Add/Remove
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (onToggleOwned != null) {
                        IconButton(
                            onClick = onToggleOwned,
                            modifier = Modifier.size(32.dp).testTag("owned_btn_${card.id}")
                        ) {
                            Icon(
                                imageVector = if (isOwned) Icons.Outlined.CheckCircle else Icons.Default.Lock,
                                contentDescription = if (isOwned) "Possui" else "Não possui",
                                tint = if (isOwned) Color(0xFF43A047) else Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    if (deckCount != null && onRemoveClick != null) {
                        IconButton(
                            onClick = onRemoveClick,
                            modifier = Modifier.size(32.dp).testTag("remove_btn_${card.id}")
                        ) {
                            Icon(
                                Icons.Default.Remove,
                                contentDescription = "Remover do deck",
                                tint = SliferRed,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Text(
                            text = "$deckCount",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }

                    if (onAddClick != null) {
                        IconButton(
                            onClick = onAddClick,
                            modifier = Modifier
                                .size(32.dp)
                                .background(MillenniumGold.copy(alpha = 0.2f), CircleShape)
                                .testTag("add_btn_${card.id}")
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Adicionar ao deck",
                                tint = MillenniumGold,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
