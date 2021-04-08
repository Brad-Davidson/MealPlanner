package com.main.mealplanner

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.storage.FirebaseStorage
import com.main.mealplanner.dto.MealPlan
import com.main.mealplanner.dto.RecipeDetails
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.service.MealPlanService
import com.main.mealplanner.service.RecipeService

class MainViewModel : ViewModel() {

    var recipes: MutableLiveData<ArrayList<RecipeHeader>> = MutableLiveData()
    var recipeService: RecipeService = RecipeService()
    
    init {
            fetchAllRecipes()
    }



    fun fetchAllRecipes(){
        recipes = recipeService.fetchRecipeHeaders()
    }
    fun fetchRecipe(recipeID: String): ArrayList<RecipeDetails> {
        return recipeService.fetchRecipeDetails(recipeID)
    }


}
