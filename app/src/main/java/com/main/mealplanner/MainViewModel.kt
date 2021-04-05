package com.main.mealplanner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.main.mealplanner.dto.MealPlan
import com.main.mealplanner.dto.RecipeDetails
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.service.RecipeService

class MainViewModel : ViewModel() {

    var recipes: MutableLiveData<ArrayList<RecipeHeader>> = MutableLiveData()
    var recipeService: RecipeService = RecipeService()
    var mealplans: MutableLiveData<ArrayList<MealPlan>> = MutableLiveData()
    lateinit var firestore : FirebaseFirestore
    
    init {

            listenToRecipes()
    }
    
    private fun listenToRecipes() {
        fetchAllRecipes()
    }

    fun fetchAllRecipes(){
        recipes = recipeService.fetchRecipeHeaders()
    }
    fun fetchRecipe(recipeID: String): ArrayList<RecipeDetails> {
        return recipeService.fetchRecipeDetails(recipeID)
    }


}
