package com.example.modul4.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Mauve,
    secondary = LightLilac,
    tertiary = BlushPink,
    background = Color(0xFF2A2A2E),
    surface = Color(0xFF2A2A2E),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Rose,
    secondary = SoftPink,
    tertiary = LightLilac,
    background = Color(0xFFFFF0F5),
    surface = Color(0xFFFFF5F7),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color(0xFF333333),
    onSurface = Color(0xFF333333)
)

@Composable
fun Modul4Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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