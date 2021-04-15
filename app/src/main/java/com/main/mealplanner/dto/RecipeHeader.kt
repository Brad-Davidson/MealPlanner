package com.main.mealplanner.dto


import com.google.gson.annotations.SerializedName

/*
Basic class that contains the bare minimal amount of info regarding a recipe.
 */
data class RecipeHeader(@SerializedName("idMeal") val recipeID: String, @SerializedName("strMeal") val name: String, @SerializedName("strMealThumb") val recipeImageUrl: String) {
    override fun toString(): String {
        return name
    }
}
