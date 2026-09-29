package com.example.petstore.models

import com.example.petstore.R


enum class PetGender (val gender: String) {
    MALE("Male"),
    FEMALE("Female")
}

enum class PetStatus (val status: String) {
    AVAILABLE("Available"),
    DONATED("Donated")
}

enum class PetSpecies (val species: String) {
    CAT("Cat"),
    DOG("Dog")
}

data class PetMedia(
    val url: String?,
    val resource: Int?,
    val type: MediaType
)

enum class MediaType {
    IMAGE,
    VIDEO
}

data class Pet(
    val id: String,
    val name: String,
    val species: String,
    val breed: String,
    val size: String,
    val gender: String,
    val description: String,
    val status: String,
    val media: List<PetMedia>
)

val pets = listOf(
    Pet(
        id = "1",
        name = "Max",
        species = PetSpecies.DOG.species,
        breed = "Golden Retriever",
        size = "Large",
        gender = PetGender.MALE.gender,
        description = "Friendly and energetic Golden Retriever who loves playing outdoors and spending time with people.",
        status = PetStatus.AVAILABLE.status,
        media = listOf(
            PetMedia(
                url = "https://img.magnific.com/foto-gratis/disparo-enfoque-superficial-lindo-cachorro-golden-retriever-sentado-suelo-hierba_181624-24655.jpg",
                resource = null,
                type = MediaType.IMAGE
            ),
            PetMedia(
                url = null,
                resource = R.raw.golden,
                type = MediaType.VIDEO
            )
        )
    ),
    Pet(
        id = "2",
        name = "Luna",
        species = PetSpecies.CAT.species,
        breed = "Siamese",
        size = "Small",
        gender = PetGender.FEMALE.gender,
        description = "Calm and affectionate Siamese cat who enjoys quiet environments and gentle attention.",
        status = PetStatus.AVAILABLE.status,
        media = listOf()
    ),
    Pet(
        id = "3",
        name = "Rocky",
        species = PetSpecies.DOG.species,
        breed = "Beagle",
        size = "Medium",
        gender = PetGender.MALE.gender,
        description = "Playful and curious Beagle with a friendly personality and lots of energy.",
        status = PetStatus.DONATED.status,
        media = listOf()
    ),
    Pet(
        id = "4",
        name = "Mia",
        species = PetSpecies.CAT.species,
        breed = "Persian",
        size = "Medium",
        gender = PetGender.FEMALE.gender,
        description = "Sweet and relaxed Persian cat who prefers calm surroundings and enjoys being pampered.",
        status = PetStatus.AVAILABLE.status,
        media = listOf()
    )
)
