package com.example.petstore.services

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petstore.models.Pet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PetViewModel : ViewModel() {

    private val _pets = MutableStateFlow<List<Pet>>(emptyList())
    val pets: StateFlow<List<Pet>> = _pets

    fun getPets() {
        viewModelScope.launch {
            try {
                val response = petApi.getPets(limit = 8)
                _pets.value = response.data
            } catch (e: Exception) {
                Log.e("Request", "Error requesting pets", e)
            }
        }
    }

    fun getPetById(id: String): Pet? {
        return _pets.value.find { it.id == id }
    }
}