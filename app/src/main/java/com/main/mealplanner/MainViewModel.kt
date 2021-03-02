package com.main.mealplanner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.service.RecipeService

class MainViewModel : ViewModel() {

    var recipes: MutableLiveData<ArrayList<RecipeHeader>> = MutableLiveData()
    var recipeService: RecipeService = RecipeService()

    fun fetchRecipe(mealName: String) {
        recipes = recipeService.fetchRecipe(mealName)
    }
    fun fetchAllRecipes(){
        recipes = recipeService.fetchRecipe("")
    }

}