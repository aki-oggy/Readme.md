package com.oggy.streaming.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val md_theme_light_primary = Color(0xFF006E3E)
private val md_theme_light_on_primary = Color(0xFFFFFFFF)
private val md_theme_light_primary_container = Color(0xFF7FFF8F)
private val md_theme_light_on_primary_container = Color(0xFF002108)
private val md_theme_light_secondary = Color(0xFF516200)
private val md_theme_light_on_secondary = Color(0xFFFFFFFF)
private val md_theme_light_secondary_container = Color(0xFFD9E78F)
private val md_theme_light_on_secondary_container = Color(0xFF191E00)
private val md_theme_light_error = Color(0xFFB3261E)
private val md_theme_light_on_error = Color(0xFFFFFFFF)
private val md_theme_light_error_container = Color(0xFFF9DEDC)
private val md_theme_light_on_error_container = Color(0xFF410E0B)
private val md_theme_light_background = Color(0xFFFFFBFE)
private val md_theme_light_on_background = Color(0xFF201A1B)
private val md_theme_light_surface = Color(0xFFFFFBFE)
private val md_theme_light_on_surface = Color(0xFF201A1B)

private val md_theme_dark_primary = Color(0xFF7FFF8F)
private val md_theme_dark_on_primary = Color(0xFF003D20)
private val md_theme_dark_primary_container = Color(0xFF005C3A)
private val md_theme_dark_on_primary_container = Color(0xFF7FFF8F)
private val md_theme_dark_secondary = Color(0xFFBDCA6B)
private val md_theme_dark_on_secondary = Color(0xFF313900)
private val md_theme_dark_secondary_container = Color(0xFF3F4900)
private val md_theme_dark_on_secondary_container = Color(0xFFBDCA6B)
private val md_theme_dark_error = Color(0xFFF2B8B5)
private val md_theme_dark_on_error = Color(0xFF601410)
private val md_theme_dark_error_container = Color(0xFF8B1A16)
private val md_theme_dark_on_error_container = Color(0xFFF2B8B5)
private val md_theme_dark_background = Color(0xFF201A1B)
private val md_theme_dark_on_background = Color(0xFFECE0E1)
private val md_theme_dark_surface = Color(0xFF201A1B)
private val md_theme_dark_on_surface = Color(0xFFECE0E1)

private val lightScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_on_primary,
    primaryContainer = md_theme_light_primary_container,
    onPrimaryContainer = md_theme_light_on_primary_container,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_on_secondary,
    secondaryContainer = md_theme_light_secondary_container,
    onSecondaryContainer = md_theme_light_on_secondary_container,
    error = md_theme_light_error,
    onError = md_theme_light_on_error,
    errorContainer = md_theme_light_error_container,
    onErrorContainer = md_theme_light_on_error_container,
    background = md_theme_light_background,
    onBackground = md_theme_light_on_background,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_on_surface,
)

private val darkScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_on_primary,
    primaryContainer = md_theme_dark_primary_container,
    onPrimaryContainer = md_theme_dark_on_primary_container,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_on_secondary,
    secondaryContainer = md_theme_dark_secondary_container,
    onSecondaryContainer = md_theme_dark_on_secondary_container,
    error = md_theme_dark_error,
    onError = md_theme_dark_on_error,
    errorContainer = md_theme_dark_error_container,
    onErrorContainer = md_theme_dark_on_error_container,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_on_background,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_on_surface,
)

@Composable
fun OggyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
