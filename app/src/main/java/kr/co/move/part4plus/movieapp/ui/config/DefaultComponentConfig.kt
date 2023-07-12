package kr.co.move.part4plus.movieapp.ui.config

import kr.co.move.part4plus.movieapp.ui.theme.Shapes
import kr.co.move.part4plus.movieapp.ui.theme.Typography
import kr.co.move.part4plus.movieapp.ui.theme.color.ColorSet


object DefaultComponentConfig {
    val RED_THEME = ComponentConfig(
        colors = ColorSet.Red,
        shapes = Shapes,
        typography = Typography,
        isDarkTheme = false
    )

    val BLUE_THEME = ComponentConfig(
        colors = ColorSet.Blue,
        shapes = Shapes,
        typography = Typography,
        isDarkTheme = false
    )

    private var currentConfig = RED_THEME
    private var themeColorSet: ColorSet = ColorSet.Red
}

