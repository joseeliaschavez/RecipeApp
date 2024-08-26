package com.rangerforce.recipeapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _mealApiClient = MealApiClient()
    private val categoryState = mutableStateOf(RecipeState())
    val category: MutableState<RecipeState> = categoryState

    init {
        fetchCategories()
    }

    data class RecipeState(
        val isLoading: Boolean = false,
        val recipes: List<Category> = emptyList(),
        val error: String? = null
    )

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                categoryState.value = RecipeState(isLoading = true)
                val response = _mealApiClient.getCategories()
                categoryState.value = RecipeState(isLoading = false, recipes = response.categories)
            } catch (e: Exception) {
                categoryState.value = RecipeState(isLoading = false, error = e.message)
            }
        }
    }
}