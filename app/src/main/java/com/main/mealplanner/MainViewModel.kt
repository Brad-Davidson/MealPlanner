package com.main.mealplanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.main.mealplanner.dto.MealPlan
import com.main.mealplanner.dto.RecipeDetails
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.service.MealPlanService
import com.main.mealplanner.service.RecipeService

class MainViewModel : ViewModel() {

    var recipes: MutableLiveData<ArrayList<RecipeHeader>> = MutableLiveData()
    var recipeService: RecipeService = RecipeService()
    var user : FirebaseUser? = null
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
