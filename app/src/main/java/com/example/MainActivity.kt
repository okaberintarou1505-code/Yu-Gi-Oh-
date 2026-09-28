package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.getPlayerLevelBadgeColor
import com.example.ui.screens.CardSearchScreen
import com.example.ui.screens.DeckBuilderScreen
import com.example.ui.screens.MetaDecksScreen
import com.example.ui.screens.ProgressionScreen
import com.example.ui.theme.MillenniumGold
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.DeckBuilderViewModel

enum class AppScreen(val title: String) {
    DECK_BUILDER("Deck Builder"),
    CARD_SEARCH("Cartas & Packs"),
    META_DECKS("Decks Meta 2008"),
    PROGRESSION("Nível & Progresso")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    viewModel: DeckBuilderViewModel = viewModel()
) {
    var currentScreen by remember { mutableStateOf(AppScreen.DECK_BUILDER) }
    val playerLevel by viewModel.playerLevel.collectAsStateWithLifecycle()

    // Handle back button for sub-screens
    if (currentScreen != AppScreen.DECK_BUILDER) {
        BackHandler {
            currentScreen = AppScreen.DECK_BUILDER
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MillenniumGold.copy(alpha = 0.2f),
                            modifier = Modifier.size(32.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "GX",
                                    fontWeight = FontWeight.Black,
                                    fontSize = 13.sp,
                                    color = MillenniumGold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "TAG FORCE BUILDER",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                actions = {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = getPlayerLevelBadgeColor(playerLevel).copy(alpha = 0.2f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, getPlayerLevelBadgeColor(playerLevel)),
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Text(
                            text = "Lvl ${playerLevel.levelNumber}",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = getPlayerLevelBadgeColor(playerLevel)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = currentScreen == AppScreen.DECK_BUILDER,
                    onClick = { currentScreen = AppScreen.DECK_BUILDER },
                    icon = { Icon(Icons.Default.Layers, contentDescription = "Meu Deck") },
                    label = { Text("Meu Deck") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MillenniumGold,
                        selectedTextColor = MillenniumGold,
                        indicatorColor = MillenniumGold.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.testTag("nav_deck_builder")
                )

                NavigationBarItem(
                    selected = currentScreen == AppScreen.CARD_SEARCH,
                    onClick = { currentScreen = AppScreen.CARD_SEARCH },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Cartas") },
                    label = { Text("Cartas") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MillenniumGold,
                        selectedTextColor = MillenniumGold,
                        indicatorColor = MillenniumGold.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.testTag("nav_card_search")
                )

                NavigationBarItem(
                    selected = currentScreen == AppScreen.META_DECKS,
                    onClick = { currentScreen = AppScreen.META_DECKS },
                    icon = { Icon(Icons.Default.Star, contentDescription = "Meta 2008") },
                    label = { Text("Meta 2008") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MillenniumGold,
                        selectedTextColor = MillenniumGold,
                        indicatorColor = MillenniumGold.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.testTag("nav_meta_decks")
                )

                NavigationBarItem(
                    selected = currentScreen == AppScreen.PROGRESSION,
                    onClick = { currentScreen = AppScreen.PROGRESSION },
                    icon = { Icon(Icons.Default.School, contentDescription = "Progresso") },
                    label = { Text("Progresso") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MillenniumGold,
                        selectedTextColor = MillenniumGold,
                        indicatorColor = MillenniumGold.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.testTag("nav_progression")
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                AppScreen.DECK_BUILDER -> {
                    DeckBuilderScreen(
                        viewModel = viewModel,
                        onNavigateToSearch = { currentScreen = AppScreen.CARD_SEARCH }
                    )
                }
                AppScreen.CARD_SEARCH -> {
                    CardSearchScreen(viewModel = viewModel)
                }
                AppScreen.META_DECKS -> {
                    MetaDecksScreen(
                        viewModel = viewModel,
                        onDeckImported = { currentScreen = AppScreen.DECK_BUILDER }
                    )
                }
                AppScreen.PROGRESSION -> {
                    ProgressionScreen(viewModel = viewModel)
                }
            }
        }
    }
}
