package se.miun.frba1901.dt031g.chattingapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val lightColorPalette = lightColors(
    primary = primaryPurple,
    secondary = textColorLight,
    surface = gray,
    primaryVariant = primaryPurpleVariant,
    onPrimary = lightBlue,
    onSurface = lightBlue
)
private val darkColorPalette = darkColors(
    primary = primaryDark,
    secondary = textColorDark,
    surface = gray,
    primaryVariant = primaryDarkVariant,
    onPrimary = lightBlue,
    onSurface = lightBlue
)

@Composable
fun ChattingAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if(darkTheme) darkColorPalette else lightColorPalette,
        typography = typography,
        content = content,
        shapes = shapes
    )
}