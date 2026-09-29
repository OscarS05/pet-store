package com.example.petstore.models

enum class ProductCategory (val category: String) {
    ACCESSORIES("Accessories"),
    FOOD("Food"),
    CLEANING("Cleaning")
}

data class Product(
    val id: String,
    val sku: String,
    val name: String,
    val price: Int,
    val category: String,
    val description: String,
    val photos: List<String>
)

val products = listOf(
    Product(
        id = "1",
        sku = "ACC-001",
        name = "Adjustable Dog Collar",
        price = 45000,
        category = ProductCategory.ACCESSORIES.category,
        description = "Comfortable and adjustable collar for dogs of different sizes.",
        photos = listOf(
            "https://cdn.shopify.com/s/files/1/0316/4854/6949/products/military-style-dog-collar-with-close-control-handle-black_300x@2x.jpg?v=1643146298"
        )
    ),
    Product(
        id = "2",
        sku = "ACC-002",
        name = "Interactive Cat Toy",
        price = 35000,
        category = ProductCategory.ACCESSORIES.category,
        description = "Interactive toy designed to keep cats active and entertained.",
        photos = listOf()
    ),
    Product(
        id = "3",
        sku = "ACC-003",
        name = "Stainless Steel Pet Bowl",
        price = 36000,
        category = ProductCategory.ACCESSORIES.category,
        description = "Durable stainless steel bowl suitable for food and water.",
        photos = listOf()
    ),
    Product(
        id = "4",
        sku = "FOOD-001",
        name = "Premium Dog Food",
        price = 80000,
        category = ProductCategory.FOOD.category,
        description = "Balanced dry food made with high-quality ingredients for adult dogs.",
        photos = listOf()
    ),
    Product(
        id = "5",
        sku = "FOOD-002",
        name = "Salmon Cat Food",
        price = 88000,
        category = ProductCategory.FOOD.category,
        description = "Nutritional cat food with salmon and essential nutrients.",
        photos = listOf()
    ),
    Product(
        id = "6",
        sku = "CLEAN-001",
        name = "Pet Shampoo",
        price = 24000,
        category = ProductCategory.CLEANING.category,
        description = "Gentle shampoo specially formulated for pets with sensitive skin.",
        photos = listOf()
    ),
    Product(
        id = "7",
        sku = "CLEAN-002",
        name = "Pet Dental Care Kit",
        price = 52000,
        category = ProductCategory.CLEANING.category,
        description = "Complete dental care kit for maintaining your pet's oral hygiene.",
        photos = listOf()
    ),
    Product(
        id = "8",
        sku = "CLEAN-003",
        name = "Paw Cleaning Foam",
        price = 30000,
        category = ProductCategory.CLEANING.category,
        description = "Easy-to-use cleaning foam for removing dirt from your pet's paws.",
        photos = listOf()
    )
)