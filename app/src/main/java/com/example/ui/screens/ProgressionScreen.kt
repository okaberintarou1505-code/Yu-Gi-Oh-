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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.data.datasource.TagForceCardDatabase
import com.example.data.model.PlayerLevel
import com.example.ui.components.getPlayerLevelBadgeColor
import com.example.ui.theme.MillenniumGold
import com.example.ui.theme.ObeliskBlue
import com.example.ui.theme.RaYellow
import com.example.ui.theme.SliferRed
import com.example.ui.viewmodel.DeckBuilderViewModel

data class TagForcePackInfo(
    val code: String,
    val name: String,
    val costDp: Int,
    val level: PlayerLevel,
    val highlights: String,
    val cardsCount: Int
)

val tagForcePacksList = listOf(
    TagForcePackInfo("01", "First Monster", 100, PlayerLevel.LEVEL_1_EARLY, "Monstros normais básicos, beater inicial de terra/trevas", 70),
    TagForcePackInfo("02", "First Spell-Trap", 100, PlayerLevel.LEVEL_1_EARLY, "Fissure, Trap Hole, Dust Tornado, Monster Reborn", 70),
    TagForcePackInfo("03", "Legend Monsters", 100, PlayerLevel.LEVEL_1_EARLY, "Dark Magician, Blue-Eyes White Dragon, Gaia", 70),
    TagForcePackInfo("04", "Fusion Monster", 100, PlayerLevel.LEVEL_1_EARLY, "Polymerization, Flame Swordsman, fusões clássicas", 60),
    TagForcePackInfo("05", "Effect Monsters", 100, PlayerLevel.LEVEL_1_EARLY, "Sangan, Man-Eater Bug, Hane-Hane, efeitos de virar", 75),
    TagForcePackInfo("06", "Step Up Monster", 100, PlayerLevel.LEVEL_1_EARLY, "Vorse Raider, Gemini Elf, Goblin Attack Force", 80),
    TagForcePackInfo("07", "Step Up Spell-Trap", 100, PlayerLevel.LEVEL_1_EARLY, "Sakuretsu Armor, Smashing Ground, Heavy Storm, MST, ROTA", 80),
    TagForcePackInfo("08", "Direct Attack", 100, PlayerLevel.LEVEL_1_EARLY, "Jinzo, Solemn Judgment, Mirror Force, Torrential Tribute", 80),
    TagForcePackInfo("11", "Soul of the Duelist", 150, PlayerLevel.LEVEL_2_MID, "Mobius the Frost Monarch, Horus the Black Flame", 60),
    TagForcePackInfo("12", "Rise of Destiny", 150, PlayerLevel.LEVEL_2_MID, "Thestalos the Firestorm Monarch, Dekoichi", 60),
    TagForcePackInfo("14", "The Lost Millennium", 150, PlayerLevel.LEVEL_2_MID, "Elemental HERO Avian, Burstinatrix, Sparkman, Ancient Gear", 60),
    TagForcePackInfo("15", "Cybernetic Revolution", 150, PlayerLevel.LEVEL_2_MID, "Cyber Dragon, Power Bond, Miracle Fusion, Cyber Twin", 60),
    TagForcePackInfo("16", "Elemental Energy", 150, PlayerLevel.LEVEL_2_MID, "Shining Flare Wingman, Pot of Avarice, Wildheart", 60),
    TagForcePackInfo("17", "Shadow of Infinity", 150, PlayerLevel.LEVEL_2_MID, "Treeborn Frog, Demise King of Armageddon", 60),
    TagForcePackInfo("18", "Enemy of Justice", 150, PlayerLevel.LEVEL_2_MID, "Zaborg the Thunder Monarch, Destiny HERO Diamond Dude", 60),
    TagForcePackInfo("19", "Power of the Duelist", 150, PlayerLevel.LEVEL_2_MID, "Destiny HERO Malicious, Destiny Draw, Overload Fusion", 60),
    TagForcePackInfo("20", "Strike of Neos", 150, PlayerLevel.LEVEL_2_MID, "Elemental HERO Stratos, Grand Mole, Gene-Warped Warwolf", 60),
    TagForcePackInfo("21", "Force of the Breaker", 150, PlayerLevel.LEVEL_2_MID, "Raiza the Storm Monarch, Crystal Beasts", 60),
    TagForcePackInfo("22", "Tactical Evolution", 150, PlayerLevel.LEVEL_2_MID, "Zombie Master, Il Blud, Rainbow Dragon", 60),
    TagForcePackInfo("23", "Gladiator's Assault", 150, PlayerLevel.LEVEL_3_ENDGAME, "Gladiator Beast Bestiari, Laquari, Prisma", 80),
    TagForcePackInfo("24", "Phantom Darkness", 150, PlayerLevel.LEVEL_3_ENDGAME, "Dark Armed Dragon, Allure of Darkness, Armageddon Knight", 80),
    TagForcePackInfo("25", "Light of Destruction", 150, PlayerLevel.LEVEL_3_ENDGAME, "Judgment Dragon, Celestia, Lumina, Honest, Caius Monarch", 80),
    TagForcePackInfo("26", "The Duelist Genesis", 150, PlayerLevel.LEVEL_3_ENDGAME, "Stardust Dragon, Goyo Guardian, Emergency Teleport, Krebons", 80),
    TagForcePackInfo("27", "Champion Pack / Promo", 200, PlayerLevel.LEVEL_3_ENDGAME, "Gladiator Beast Gyzarus, War Chariot, Mezuki, Plaguespreader", 50)
)

@Composable
fun ProgressionScreen(
    viewModel: DeckBuilderViewModel
) {
    val currentLevel by viewModel.playerLevel.collectAsStateWithLifecycle()
    val ownedCardIds by viewModel.ownedCardIds.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
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
                            Icons.Default.School,
                            contentDescription = null,
                            tint = MillenniumGold,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Nível do Duelista & Progressão",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Filtro de cartas por Booster Packs do Tag Force 3",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Level Selector Cards
            item {
                Text(
                    text = "Escolha seu Nível de Jogo Atual",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "O sistema usará isso para sugerir apenas cartas e substituições que você já pode obter!",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            items(PlayerLevel.values()) { level ->
                val isSelected = currentLevel == level
                val levelColor = getPlayerLevelBadgeColor(level)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            2.dp,
                            if (isSelected) levelColor else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { viewModel.setPlayerLevel(level) }
                        .testTag("select_level_${level.levelNumber}"),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) levelColor.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = level.title,
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = if (isSelected) levelColor else MaterialTheme.colorScheme.onSurface
                                )
                                if (isSelected) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = levelColor
                                    ) {
                                        Text(
                                            text = "ATIVO",
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = level.subtitle,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = level.packsUnlocked,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Icon(
                            imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = if (isSelected) levelColor else Color.Transparent,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            // Quick Unlock Button
            item {
                Button(
                    onClick = { viewModel.unlockAllCardsForLevel(currentLevel) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("unlock_level_cards_btn"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MillenniumGold,
                        contentColor = Color(0xFF1E1400)
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.LockOpen, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Marcar Todas as Cartas do ${currentLevel.subtitle} como Obtidas",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Booster Packs Guide Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.ShoppingBag, contentDescription = null, tint = MillenniumGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Guia de Booster Packs (GameFAQs TF3)",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

            items(tagForcePacksList) { pack ->
                val isPackUnlocked = currentLevel.levelNumber >= pack.level.levelNumber
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isPackUnlocked) Color(0xFF43A047).copy(alpha = 0.5f) else Color.Gray.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = getPlayerLevelBadgeColor(pack.level).copy(alpha = 0.2f)
                                ) {
                                    Text(
                                        text = "[${pack.code}]",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = getPlayerLevelBadgeColor(pack.level),
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = pack.name,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            Text(
                                text = "${pack.costDp} DP",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelMedium,
                                color = MillenniumGold
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = pack.highlights,
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
