package com.example.petstore.services

import com.example.petstore.models.Pet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class PetsResponse(
    val data: List<Pet>
)

interface PetApiService {

    @GET("pets")
    suspend fun getPets(
        @Query("limit") limit: Int
    ): PetsResponse

    @GET("pets/{id}")
    suspend fun getPetById(
        @Path("id") id: String
    ): Pet
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.petstoreapi.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val petApi = retrofit.create(PetApiService::class.java)
