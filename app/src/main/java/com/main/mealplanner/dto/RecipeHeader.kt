package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RecipeHeader(@SerializedName("idMeal") val recipeID: String, @SerializedName("strMeal") val name: String, @SerializedName("strMealThumb") val recipeImageUrl: String) {
    override fun toString(): String {
        return name
    }
}
