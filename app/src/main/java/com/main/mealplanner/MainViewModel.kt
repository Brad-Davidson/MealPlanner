package com.main.mealplanner

import androidx.lifecycle.MutableLiveData
import com.main.mealplanner.dto.Recipe
import com.main.mealplanner.service.RecipeService

class MainViewModel {

    var recipes: MutableLiveData<ArrayList<Recipe>> = MutableLiveData()
    var recipeService: RecipeService = RecipeService()

    fun fetchRecipe(s: String) {
        recipes = recipeService.fetchRecipe(s)
    }
    fun fetchAllRecipes(){
        recipes = recipeService.fetchRecipe("")
    }

}