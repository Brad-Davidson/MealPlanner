package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity(tableName="recipedetails") I don't believe we need to save this to a entity table
/**
 *
 */
data class RecipeDetails(
        @SerializedName("idMeal") val recipeID: String
        , @SerializedName("strArea") val area: String
        , @SerializedName("strCategory") val category: String
        , @SerializedName("strMeal") val name: String
        , @SerializedName("strIngredient1") val ingredient1: String
        , @SerializedName("strIngredient2") val ingredient2: String
        , @SerializedName("strIngredient3") val ingredient3: String
        , @SerializedName("strIngredient4") val ingredient4: String
        , @SerializedName("strIngredient5") val ingredient5: String
        , @SerializedName("strIngredient6") val ingredient6: String
        , @SerializedName("strIngredient7") val ingredient7: String
        , @SerializedName("strIngredient8") val ingredient8: String
        , @SerializedName("strIngredient9") val ingredient9: String
        , @SerializedName("strIngredient10") val ingredient10: String
        , @SerializedName("strIngredient11") val ingredient11: String
        , @SerializedName("strIngredient12") val ingredient12: String
        , @SerializedName("strIngredient13") val ingredient13: String
        , @SerializedName("strIngredient14") val ingredient14: String
        , @SerializedName("strIngredient15") val ingredient15: String
        , @SerializedName("strIngredient16") val ingredient16: String
        , @SerializedName("strIngredient17") val ingredient17: String
        , @SerializedName("strIngredient18") val ingredient18: String
        , @SerializedName("strIngredient19") val ingredient19: String
        , @SerializedName("strIngredient20") val ingredient20: String
        , @SerializedName("strMeasure1") val measurement1: String
        , @SerializedName("strMeasure2") val measurement2: String
        , @SerializedName("strMeasure3") val measurement3: String
        , @SerializedName("strMeasure4") val measurement4: String
        , @SerializedName("strMeasure5") val measurement5: String
        , @SerializedName("strMeasure6") val measurement6: String
        , @SerializedName("strMeasure7") val measurement7: String
        , @SerializedName("strMeasure8") val measurement8: String
        , @SerializedName("strMeasure9") val measurement9: String
        , @SerializedName("strMeasure10") val measurement10: String
        , @SerializedName("strMeasure11") val measurement11: String
        , @SerializedName("strMeasure12") val measurement12: String
        , @SerializedName("strMeasure13") val measurement13: String
        , @SerializedName("strMeasure14") val measurement14: String
        , @SerializedName("strMeasure15") val measurement15: String
        , @SerializedName("strMeasure16") val measurement16: String
        , @SerializedName("strMeasure17") val measurement17: String
        , @SerializedName("strMeasure18") val measurement18: String
        , @SerializedName("strMeasure19") val measurement19: String
        , @SerializedName("strMeasure20") val measurement20: String
        , @SerializedName("strInstructions") var instructions: String
        , @SerializedName("strMealThumb") val recipeImageUrl: String
) {
    override fun toString(): String {
        return "Recipe Name: $name, Recipe Category: $category"
    }
}
