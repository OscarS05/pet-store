package com.example.petstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.petstore.navigation.AppNavigation
import com.example.petstore.ui.theme.PetStoreTheme
import com.example.petstore.components.AppBottomBar
import com.example.recipesapp.components.AppTopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            PetStoreTheme {
                PetStoreApp()
            }
        }
    }
}

@Composable
fun PetStoreApp () {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            AppTopBar()
        },
        bottomBar = {
            AppBottomBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        AppNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}