package com.main.mealplanner

import androidx.lifecycle.MutableLiveData
import org.junit.Test
import com.main.mealplanner.dto.Recipe
import com.main.mealplanner.service.RecipeService
import org.junit.Assert.*
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.rules.TestRule

class RecipeUnitTests {

    //var recipeService = mockk<RecipeService>();

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var mvm:MainViewModel

    private fun givenAFeedOfRecipeDataAvailable(){
        mvm = MainViewModel()
    }

    /*
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
     */

    @Test
    fun confirmSpaghetti_outputsSpaghetti(){
        var spaghetti = Recipe("Spaghetti", "Italian", 1700, "Spaghetti Sauce, Spaghetti, Salt, Meatballs", "Cook Spaghetti", 10)
        assertEquals("Spaghetti", spaghetti.Meal)
    }

    @Test
    fun searchForSpaghetti_returnsSpaghetti(){
        givenAFeedOfRecipeDataAvailable()
        mvm.fetchRecipe("Spaghetti")
        thenResultsContainSpaghetti()
        verify {mvm.fetchRecipe("Spaghetti")}
        confirmVerified()
    }
    private fun thenResultsContainSpaghetti(){
        var spaghettiFound = false;
        mvm.recipes.observeForever {
            assertNotNull(it)
            assertTrue(it.size > 0)
            it.forEach {
                if (it.Meal == "Spaghetti" && it.Country == "Italian" && it.RecipeId == 10){
                    spaghettiFound = true;
                }
            }
        }
        assertTrue(spaghettiFound)
    }

    @Test
    fun searchForGarbage_returnsNothing(){
        givenAFeedOfMockedRecipeDataAvailable()
        mvm.fetchRecipe("aslkjgaoh")
        thenIGetNoResults()
    }

    private fun givenAFeedOfMockedRecipeDataAvailable() {
        TODO("Not yet implemented")
    }

    private fun thenIGetNoResults(){
        mvm.recipes.observeForever {
            assertEquals(0, it.size)
        }
    }

}