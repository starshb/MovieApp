package kr.co.move.part4plus.movieapp.ui.config

import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import kr.co.move.part4plus.movieapp.ui.theme.color.ColorSet

data class ComponentConfig(
    val colors: ColorSet,
    val typography: Typography,
    val shapes: Shapes,
    val isDarkTheme: Boolean
)
