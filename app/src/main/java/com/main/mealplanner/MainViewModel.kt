package com.main.mealplanner

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.main.mealplanner.dto.RecipeDetails
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.service.RecipeService

class MainViewModel : ViewModel() {
    var mealplans: MutableLiveData<ArrayList<MealPlan>> = MutableLiveData()
    var recipes: MutableLiveData<ArrayList<RecipeHeader>> = MutableLiveData()
    var recipeService: RecipeService = RecipeService()
    var mealplanService: MealPlanService = MealPlanService()
    lateinit var firestore : FirebaseFirestore
    
    init {
//            firestore = FirebaseFirestore.getInstance()
//            firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
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

//     fun save(recipe: Recipe) {
//         val document = firestore.collection(collectionPath: "recipes").document()
//         recipe.recipeID = document.id
//         val set = document.set(recipe)
//            set.addOnSuccessListener { it: Void!
//                Log.d(tag: "Firebase", msg: "document saved")
//            }
//            set.addOnFailureListener { it: Exception
//                Log.d(tag: "Firebase", msg: "save failed")
//                                     }
//     }
}
