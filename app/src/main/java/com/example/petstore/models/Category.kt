package com.example.petstore.models

import com.example.petstore.R

data class Category(
    val name: String,
    val description: String,
    val icon: Int,
    val imageBackground: Int,
    val onClick: () -> Unit
)