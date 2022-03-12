package com.example.compose_movie.ui.component.navgraph

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val icon: ImageVector,
    val activeColor: Color,
    val inactiveColor: Color,
    val destination: Screen
)
