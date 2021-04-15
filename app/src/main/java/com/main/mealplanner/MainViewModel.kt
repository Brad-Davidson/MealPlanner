package com.main.mealplanner


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.main.mealplanner.dto.RecipeDetails
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.service.RecipeService

class MainViewModel : ViewModel() {

    var recipes: MutableLiveData<ArrayList<RecipeHeader>> = MutableLiveData()
    var recipeService: RecipeService = RecipeService()
    init {
            listenToRecipes()
    }
    
    private fun listenToRecipes() {
        fetchAllRecipes()
    }


    /*
    Get all recipes and put them into a mutable live data list.
     */
    fun fetchAllRecipes(){
        recipes = recipeService.fetchRecipeHeaders()
    }

    /*
    Get the details of a single recipe
     */
    suspend fun fetchRecipe(recipeID: String): RecipeDetails {
        return recipeService.fetchRecipeDetails(recipeID)
    }


}
