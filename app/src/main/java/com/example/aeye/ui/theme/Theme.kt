package com.example.aeye.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val primaryColor = AppColors.primary
val primaryDarkColor = AppColors.primaryDark
val accentColor = AppColors.accent

private val DarkColorScheme = darkColorScheme(
    primary = primaryColor,
    secondary = AppColors.textSecondary,
    tertiary = accentColor,
    background = AppColors.windowBackground,
    surface = AppColors.windowBackground,
    onPrimary = AppColors.textPrimary,
    onSecondary = AppColors.textPrimary,
    onTertiary = AppColors.textPrimary,
    onBackground = AppColors.textPrimary,
    onSurface = AppColors.textPrimary
)

private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    secondary = AppColors.textSecondary,
    tertiary = accentColor,
    background = AppColors.windowBackground,
    surface = AppColors.windowBackground,
    onPrimary = AppColors.textPrimary,
    onSecondary = AppColors.textPrimary,
    onTertiary = AppColors.textPrimary,
    onBackground = AppColors.textPrimary,
    onSurface = AppColors.textPrimary
)

@Composable
fun AEyeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
