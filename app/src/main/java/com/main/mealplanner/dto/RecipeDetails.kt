package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity(tableName="recipedetails") I don't believe we need to save this to a entity table
/**
 *
 */
data class RecipeDetails(
        @SerializedName("idMeal") val recipeID: String? = null
        , @SerializedName("strArea") val area: String? = null
        , @SerializedName("strCategory") val category: String? = null
        , @SerializedName("strMeal") val name: String? = null
        , @SerializedName("strIngredient1") val ingredient1: String? = null
        , @SerializedName("strIngredient2") val ingredient2: String? = null
        , @SerializedName("strIngredient3") val ingredient3: String? = null
        , @SerializedName("strIngredient4") val ingredient4: String? = null
        , @SerializedName("strIngredient5") val ingredient5: String? = null
        , @SerializedName("strIngredient6") val ingredient6: String? = null
        , @SerializedName("strIngredient7") val ingredient7: String? = null
        , @SerializedName("strIngredient8") val ingredient8: String? = null
        , @SerializedName("strIngredient9") val ingredient9: String? = null
        , @SerializedName("strIngredient10") val ingredient10: String? = null
        , @SerializedName("strIngredient11") val ingredient11: String? = null
        , @SerializedName("strIngredient12") val ingredient12: String? = null
        , @SerializedName("strIngredient13") val ingredient13: String? = null
        , @SerializedName("strIngredient14") val ingredient14: String? = null
        , @SerializedName("strIngredient15") val ingredient15: String? = null
        , @SerializedName("strIngredient16") val ingredient16: String? = null
        , @SerializedName("strIngredient17") val ingredient17: String? = null
        , @SerializedName("strIngredient18") val ingredient18: String? = null
        , @SerializedName("strIngredient19") val ingredient19: String? = null
        , @SerializedName("strIngredient20") val ingredient20: String? = null
        , @SerializedName("strMeasure1") val measurement1: String? = null
        , @SerializedName("strMeasure2") val measurement2: String? = null
        , @SerializedName("strMeasure3") val measurement3: String? = null
        , @SerializedName("strMeasure4") val measurement4: String? = null
        , @SerializedName("strMeasure5") val measurement5: String? = null
        , @SerializedName("strMeasure6") val measurement6: String? = null
        , @SerializedName("strMeasure7") val measurement7: String? = null
        , @SerializedName("strMeasure8") val measurement8: String? = null
        , @SerializedName("strMeasure9") val measurement9: String? = null
        , @SerializedName("strMeasure10") val measurement10: String? = null
        , @SerializedName("strMeasure11") val measurement11: String? = null
        , @SerializedName("strMeasure12") val measurement12: String? = null
        , @SerializedName("strMeasure13") val measurement13: String? = null
        , @SerializedName("strMeasure14") val measurement14: String? = null
        , @SerializedName("strMeasure15") val measurement15: String? = null
        , @SerializedName("strMeasure16") val measurement16: String? = null
        , @SerializedName("strMeasure17") val measurement17: String? = null
        , @SerializedName("strMeasure18") val measurement18: String? = null
        , @SerializedName("strMeasure19") val measurement19: String? = null
        , @SerializedName("strMeasure20") val measurement20: String? = null
        , @SerializedName("strInstructions") var instructions: String? = null
        , @SerializedName("strMealThumb") val recipeImageUrl: String? = null
        , @SerializedName("dateModified") val dateModified: String? = null
        , @SerializedName("strCreativeCommonsConfirmed") val creativeCommonsConfirmed: String? = null
        , @SerializedName("strDrinkAlternate") val drinkAlternate: String? = null
        , @SerializedName("strSource") val source: String? = null
        , @SerializedName("strTags") val tags: String? = null
        , @SerializedName("strImageSource") val imageSource: String? = null
        , @SerializedName("strYoutube") val youtube: String? = null
) {
    override fun toString(): String {
        return "Recipe Name: $name, Recipe Category: $category"
    }
}
