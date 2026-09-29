package com.example.petstore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petstore.models.pets
import com.example.petstore.models.products
import com.example.petstore.screens.HomeScreen
import com.example.petstore.screens.PetDetailScreen
import com.example.petstore.screens.PetListScreen
import com.example.petstore.screens.ProductDetailScreen
import com.example.petstore.screens.ProductListScreen


sealed class Screen(val route: String) {

    data object Home : Screen("home")

    data object ProductList : Screen("products")
    data object PetList : Screen("pets")

    data object ProductDetail : Screen("products/{productId}") {
        fun createRoute(productId: String): String {
            return "products/$productId"
        }
    }

    data object PetDetail : Screen("pets/{petId}") {
        fun createRoute(petId: String): String {
            return "pets/$petId"
        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onProductCategoryClick = {
                    navController.navigate(Screen.ProductList.route)
                },
                onPetCategoryClick = {
                    navController.navigate(Screen.PetList.route)
                }
            )
        }

        composable(route = Screen.ProductList.route) {
            ProductListScreen(
                onProductClick = { productId: String ->
                    navController.navigate(
                        Screen.ProductDetail.createRoute(productId)
                    )
                }
            )
        }

        composable(route = Screen.PetList.route) {
            PetListScreen(
                onPetClick = { petId: String ->
                    navController.navigate(
                        Screen.PetDetail.createRoute(petId)
                    )
                },
            )
        }

        composable(route = Screen.ProductDetail.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")

            val product = products.find { it.id == productId }

            if (product != null) {
                ProductDetailScreen(
                    product = product
                )
            } else {
                navController.popBackStack()
            }
        }

        composable(route = Screen.PetDetail.route) { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId")
            val pet = pets.find { it.id == petId }

            if (pet != null) {
                PetDetailScreen(
                    pet = pet
                )
            } else {
                navController.popBackStack()
            }
        }
    }
}