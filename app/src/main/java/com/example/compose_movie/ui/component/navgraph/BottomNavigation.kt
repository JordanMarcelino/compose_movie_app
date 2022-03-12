package com.example.compose_movie.ui.component.navgraph

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationView(
    navigationList: List<BottomNavItem> = listOf(
        BottomNavItem(
            icon = Icons.Default.Home,
            activeColor = MaterialTheme.colors.primary,
            inactiveColor = MaterialTheme.colors.onPrimary,
            destination = Screen.Home
        ),
        BottomNavItem(
            icon = Icons.Default.Search,
            activeColor = MaterialTheme.colors.primary,
            inactiveColor = MaterialTheme.colors.onPrimary,
            destination = Screen.Search
        ),
        BottomNavItem(
            icon = Icons.Default.Favorite,
            activeColor = MaterialTheme.colors.primary,
            inactiveColor = MaterialTheme.colors.onPrimary,
            destination = Screen.Favourite
        )
    ),
    navController: NavHostController,
    onClicked: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        backgroundColor = Color(
            0xFF0a9396
        ).copy(alpha = 0.55f),
        elevation = 10.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(28.dp))
            .padding(6.dp)
    ) {
        navigationList.forEach { item ->
            val selected = item.destination.navigation == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onClicked(item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = if (selected) item.activeColor else item.inactiveColor,
                        modifier = Modifier.size(28.dp)
                    )
                },
            )
        }
    }
}