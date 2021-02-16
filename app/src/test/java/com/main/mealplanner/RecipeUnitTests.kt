package com.main.mealplanner

import androidx.lifecycle.MutableLiveData
import org.junit.Test
import com.main.mealplanner.dto.
import com.main.mealplanner.dto.Recipe
import org.junit.Assert.*
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.rules.TestRule

class RecipeUnitTests {

    var recipeService = mockk<RecipeService>();

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var mvm:MainViewModel

    private fun givenAFeedOfMockedRecipeDataAvailable(){
        mvm = MainViewModel()
        createMockData()
    }

    //creates some dummy data
    private fun createMockData(){
        var allRecipeLiveData = MutableLiveData<ArrayList<Recipe>>()
        var allRecipes = ArrayList<Recipe>()

        var spaghetti = Recipe("Spaghetti", "Italian", 1700, "Spaghetti Sauce, Spaghetti, Salt, Meatballs", "Cook Spaghetti", 10)
        allRecipes.add(spaghetti)
        allRecipeLiveData.postValue(allRecipes)
        every {recipeService.fetchRecipe("Spaghetti")} returns allRecipeLiveData
        every {recipeService.fetchRecipe(not("Spaghetti"))} returns MutableLiveData<ArrayList<Recipe>>()
        mvm.recipeService = recipeService
    }

    @Test
    fun searchForGarbage_returnsNothing(){

    }

}