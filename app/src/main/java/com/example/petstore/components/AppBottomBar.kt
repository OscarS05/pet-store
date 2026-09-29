package com.example.petstore.components

import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.petstore.navigation.Screen
import com.example.petstore.ui.theme.ColorIconNotSelected
import com.example.petstore.ui.theme.Primary


@Composable
fun AppBottomBar(
    navController: NavHostController
) {
    NavigationBar() {
        NavigationBarItem(
            selected = true,
            onClick = {
                navController.navigate(Screen.Home.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Primary
                )
            },
            label = {
                Text(
                    text = "Home",
                    color = Primary
                )
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites",
                    tint = ColorIconNotSelected
                )
            }
        )
    }
}