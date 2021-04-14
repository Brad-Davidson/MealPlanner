package com.main.mealplanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
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
            user = FirebaseAuth.getInstance().currentUser
    }
    
    private fun listenToRecipes() {
        fetchAllRecipes()
    }



    fun fetchAllRecipes(){
        recipes = recipeService.fetchRecipeHeaders()
    }
    //This is a synchronous call. Ideally, this would be asynchronous and hooked up to mutable live data, HOWEVER with the usage of this method as is (a helper function used within multiple fragment methods)
    //I have no idea how to make this a coroutine while keeping it able to be called inline
    suspend fun fetchRecipe(recipeID: String): RecipeDetails {
        return recipeService.fetchRecipeDetails(recipeID)
    }


}
