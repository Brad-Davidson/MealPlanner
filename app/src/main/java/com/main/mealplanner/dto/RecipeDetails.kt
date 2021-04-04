package com.main.mealplanner.dto

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

        fun getIngredients(): ArrayList<Ingredient>{
                var ingredients = ArrayList<Ingredient>()

                //combine all non-null ingredients with their measurement
                if(ingredient1 != "" && measurement1 != "") ingredients.add(Ingredient("", measurement1, ingredient1))
                if(ingredient2 != "" && measurement2 != "") ingredients.add(Ingredient("", measurement2, ingredient2))
                if(ingredient3 != "" && measurement3 != "") ingredients.add(Ingredient("", measurement3, ingredient3))
                if(ingredient4 != "" && measurement4 != "") ingredients.add(Ingredient("", measurement4, ingredient4))
                if(ingredient5 != "" && measurement5 != "") ingredients.add(Ingredient("", measurement5, ingredient5))
                if(ingredient6 != "" && measurement6 != "") ingredients.add(Ingredient("", measurement6, ingredient6))
                if(ingredient7 != "" && measurement7 != "") ingredients.add(Ingredient("", measurement7, ingredient7))
                if(ingredient8 != "" && measurement8 != "") ingredients.add(Ingredient("", measurement8, ingredient8))
                if(ingredient9 != "" && measurement9 != "") ingredients.add(Ingredient("", measurement9, ingredient9))
                if(ingredient10 != "" && measurement10 != "") ingredients.add(Ingredient("", measurement10, ingredient10))
                if(ingredient11 != "" && measurement11 != "") ingredients.add(Ingredient("", measurement11, ingredient11))
                if(ingredient12 != "" && measurement12 != "") ingredients.add(Ingredient("", measurement12, ingredient12))
                if(ingredient13 != "" && measurement13 != "") ingredients.add(Ingredient("", measurement13, ingredient13))
                if(ingredient14 != "" && measurement14 != "") ingredients.add(Ingredient("", measurement14, ingredient14))
                if(ingredient15 != "" && measurement15 != "") ingredients.add(Ingredient("", measurement15, ingredient15))
                if(ingredient16 != "" && measurement16 != "") ingredients.add(Ingredient("", measurement16, ingredient16))
                if(ingredient17 != "" && measurement17 != "") ingredients.add(Ingredient("", measurement17, ingredient17))
                if(ingredient18 != "" && measurement18 != "") ingredients.add(Ingredient("", measurement18, ingredient18))
                if(ingredient19 != "" && measurement19 != "") ingredients.add(Ingredient("", measurement19, ingredient19))
                if(ingredient20 != "" && measurement20 != "") ingredients.add(Ingredient("", measurement20, ingredient20))

        return ingredients
    }

//TODO() I Think using a funcation like this would clean up your code and avoid so many if statements, its up to you guys want use its up to preference

    fun exampleFuncation(ingredients: ArrayList<String>, measurements:ArrayList<String>): ArrayList<Ingredient> {
        var ingredientsList = ArrayList<Ingredient>()
        var curMeasurement: String
        var curIngredient:String

        for(i in 0..ingredients.size-1)
        {
            curMeasurement= measurements[i]
            curIngredient= ingredients[i]

            if (curMeasurement != "" && curIngredient != "") ingredientsList.add(
                Ingredient(
                    "",
                    curMeasurement,
                    curIngredient
                )
            )
        }
        return ingredientsList

    }



}
