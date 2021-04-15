package com.main.mealplanner.dto

import com.google.gson.annotations.SerializedName

/*
A detailed description of a recipe and its ingredients
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
        /*
        Aggregate the ingredients to make managing on the front end much easier.
         */
        fun getIngredients(): ArrayList<Ingredient>{
                var ingredients = ArrayList<Ingredient>()

                //combine all non-null ingredients with their measurement
                if(ingredient1 != "" && measurement1 != "" && ingredient1 != null && measurement1 != null) ingredients.add(Ingredient("", measurement1, ingredient1))
                if(ingredient2 != "" && measurement2 != "" && ingredient2 != null && measurement2 != null) ingredients.add(Ingredient("", measurement2, ingredient2))
                if(ingredient3 != "" && measurement3 != "" && ingredient3 != null && measurement3 != null) ingredients.add(Ingredient("", measurement3, ingredient3))
                if(ingredient4 != "" && measurement4 != "" && ingredient4 != null && measurement4 != null) ingredients.add(Ingredient("", measurement4, ingredient4))
                if(ingredient5 != "" && measurement5 != "" && ingredient5 != null && measurement5 != null) ingredients.add(Ingredient("", measurement5, ingredient5))
                if(ingredient6 != "" && measurement6 != "" && ingredient6 != null && measurement6 != null) ingredients.add(Ingredient("", measurement6, ingredient6))
                if(ingredient7 != "" && measurement7 != "" && ingredient7 != null && measurement7 != null) ingredients.add(Ingredient("", measurement7, ingredient7))
                if(ingredient8 != "" && measurement8 != "" && ingredient8 != null && measurement8 != null) ingredients.add(Ingredient("", measurement8, ingredient8))
                if(ingredient9 != "" && measurement9 != "" && ingredient9 != null && measurement9 != null) ingredients.add(Ingredient("", measurement9, ingredient9))
                if(ingredient10 != "" && measurement10 != "" && ingredient10 != null && measurement10 != null) ingredients.add(Ingredient("", measurement10, ingredient10))
                if(ingredient11 != "" && measurement11 != "" && ingredient11 != null && measurement11 != null) ingredients.add(Ingredient("", measurement11, ingredient11))
                if(ingredient12 != "" && measurement12 != "" && ingredient12 != null && measurement12 != null) ingredients.add(Ingredient("", measurement12, ingredient12))
                if(ingredient13 != "" && measurement13 != "" && ingredient13 != null && measurement13 != null) ingredients.add(Ingredient("", measurement13, ingredient13))
                if(ingredient14 != "" && measurement14 != "" && ingredient14 != null && measurement14 != null) ingredients.add(Ingredient("", measurement14, ingredient14))
                if(ingredient15 != "" && measurement15 != "" && ingredient15 != null && measurement15 != null) ingredients.add(Ingredient("", measurement15, ingredient15))
                if(ingredient16 != "" && measurement16 != "" && ingredient16 != null && measurement16 != null) ingredients.add(Ingredient("", measurement16, ingredient16))
                if(ingredient17 != "" && measurement17 != "" && ingredient17 != null && measurement17 != null) ingredients.add(Ingredient("", measurement17, ingredient17))
                if(ingredient18 != "" && measurement18 != "" && ingredient18 != null && measurement18 != null) ingredients.add(Ingredient("", measurement18, ingredient18))
                if(ingredient19 != "" && measurement19 != "" && ingredient19 != null && measurement19 != null) ingredients.add(Ingredient("", measurement19, ingredient19))
                if(ingredient20 != "" && measurement20 != "" && ingredient20 != null && measurement20 != null) ingredients.add(Ingredient("", measurement20, ingredient20))

                return ingredients
        }
}
