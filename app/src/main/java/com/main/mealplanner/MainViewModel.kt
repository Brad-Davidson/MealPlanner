package com.main.mealplanner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.service.RecipeService

class MainViewModel : ViewModel() {

    var recipes: MutableLiveData<ArrayList<RecipeHeader>> = MutableLiveData()
    var recipeService: RecipeService = RecipeService()
    
    init {
        fetchRecipes(name: "e")
            firestore = FirebaseFirestore.getInstance()
            firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    fun fetchRecipe(s: String) {
        recipes = recipeService.fetchRecipe(s)
    }
    fun fetchAllRecipes(){
        recipes = recipeService.fetchRecipe("")
    }

}
