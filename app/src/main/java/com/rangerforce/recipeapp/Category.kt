package com.rangerforce.recipeapp

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory")
    val id: Int,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryDescription")
    val description: String,
    @SerializedName("strCategoryThumb")
    val thumbnailUrl: String,
)

data class CategoriesResponse(val categories: List<Category>)
