package com.example.petstore.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.petstore.R
import com.example.petstore.components.CategoryCard
import com.example.petstore.models.Category
import com.example.petstore.ui.theme.PageSubtitleColor

@Composable
fun HomeScreen (
    onProductCategoryClick: () -> Unit,
    onPetCategoryClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(18.dp)
    ) {
        Text(
            text = "What does your pet need today?",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Explore products, discover new favorites, and find everything your pet needs.",
            style = MaterialTheme.typography.titleMedium,
            color = PageSubtitleColor
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 320.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(
                listOf(
                    Category (
                        name = "Products",
                        description = "List of products for your pets",
                        icon = R.drawable.store,
                        imageBackground = R.drawable.product_category,
                        onClick = onProductCategoryClick
                    ),
                    Category (
                        name = "Pets",
                        description = "List of pets available for adoption",
                        icon = R.drawable.pet_icon,
                        imageBackground = R.drawable.pet_category,
                        onClick = onPetCategoryClick
                    )
                )
            ) { category ->
                CategoryCard(data = category)
            }
        }
    }
}