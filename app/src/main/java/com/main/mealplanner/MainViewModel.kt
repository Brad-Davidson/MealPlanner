package com.main.mealplanner

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
    lateinit var firestore : FirebaseFirestore
    var storageReferenence = FirebaseStorage.getInstance().getReference()
    var mealplans : MutableLiveData<ArrayList<MealPlan>> = MutableLiveData<ArrayList<MealPlan>>()
    var mealplan = MealPlan()
    
    init {
            firestore = FirebaseFirestore.getInstance()
            firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
            listenToRecipes()
            listenToMealPlans()
    }
    
    private fun listenToRecipes() {
        fetchAllRecipes()
    }

    fun save(
        mealplan: MealPlan,
        user: FirebaseUser
    ) {
        val document =
            if (mealplan.mealplanId != null && !mealplan.mealplanId.isEmpty()) {
                // updating existing
                firestore.collection("mealplans").document(mealplan.mealplanId)
            } else {
                // create new
                firestore.collection("mealplans").document()
            }
        mealplan.mealplanId = document.id
        val set = document.set(mealplan)
            set.addOnSuccessListener {
                Log.d("Firebase", "document saved")
    }

    fun fetchAllRecipes(){
        recipes = recipeService.fetchRecipeHeaders()
    }
    fun fetchRecipe(recipeID: String): ArrayList<RecipeDetails> {
        return recipeService.fetchRecipeDetails(recipeID)
    }


}
