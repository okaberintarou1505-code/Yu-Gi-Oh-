package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DuelDarkColorScheme = darkColorScheme(
    primary = MillenniumGold,
    onPrimary = Color(0xFF1E1400),
    primaryContainer = Color(0xFF533B00),
    onPrimaryContainer = Color(0xFFFFDEA3),
    secondary = SliferRed,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF5C0E0E),
    onSecondaryContainer = Color(0xFFFFDAD6),
    tertiary = ObeliskBlue,
    onTertiary = Color.White,
    background = DuelDarkBackground,
    onBackground = TextPrimaryDark,
    surface = DuelCardSurface,
    onSurface = TextPrimaryDark,
    surfaceVariant = DuelSurfaceVariant,
    onSurfaceVariant = TextSecondaryDark,
    outline = DuelSurfaceBorder
)

private val DuelLightColorScheme = lightColorScheme(
    primary = Color(0xFFB57D00),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDEA3),
    onPrimaryContainer = Color(0xFF271900),
    secondary = SliferRed,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFDAD6),
    onSecondaryContainer = Color(0xFF410002),
    tertiary = ObeliskBlue,
    onTertiary = Color.White,
    background = Color(0xFFF6F8FC),
    onBackground = Color(0xFF111827),
    surface = Color.White,
    onSurface = Color(0xFF111827),
    surfaceVariant = Color(0xFFE2E8F0),
    onSurfaceVariant = Color(0xFF475569),
    outline = Color(0xFFCBD5E1)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Yu-Gi-Oh card styling looks best in dark mode
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DuelDarkColorScheme else DuelLightColorScheme
    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
