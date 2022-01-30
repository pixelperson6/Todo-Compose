package com.codingwithpix3l.todo_compose.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFCFCFCF)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val LowPriorityColor = Color(0xFF0F810B)
val HighPriorityColor = Color(0xFF810B0B)
val MediumPriorityColor = Color(0xFFDDE405)
val NonePriorityColor = Color(0xFFFFFFFF)

val Colors.topAppbarContentColor: Color
    @Composable
    get() = if (isLight) Color.White else LightGray


val Colors.topAppbarBackgroundColor: Color
    @Composable
    get() = if (isLight) Purple500 else Color.Black

val Colors.fabBackgroundColor: Color
    @Composable
    get() = if (isLight) Teal200 else Purple700
