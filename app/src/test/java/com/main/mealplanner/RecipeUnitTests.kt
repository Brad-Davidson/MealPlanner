package com.main.mealplanner

import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.main.mealplanner.dto.RecipeDetails
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.service.RecipeService
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

class RecipeUnitTests {


    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var mvm:MainViewModel
    var recipeService: RecipeService = RecipeService()



    private fun givenAFeedOfRecipeDataAvailable(){
        mvm = MainViewModel()

    }

    @Test
    fun confirmSpaghetti_outputsSpaghetti(){
        var spaghetti = RecipeHeader("123", "Spaghetti", "google.com")
        assertEquals("Spaghetti", spaghetti.name)
    }

    @Test
    fun confirmSpaghettiInstructions_outputsInstructions(){
        //this is kinda a ridiculous way to construct a class, look into making all of the variables optional.
        var spaghetti = RecipeDetails()
        spaghetti.instructions = "test"
        assertEquals("test", spaghetti.instructions)
    }

    @Test
    fun searchForSpaghetti_returnsSpaghetti(){
        givenAFeedOfRecipeDataAvailable()
        runBlocking {
            launch(Dispatchers.IO){
                var detail = recipeService.fetchRecipeDetails("52770")//id for spaghetti bolognese
                thenResultsContainSpaghetti(detail)
            }
        }

    }
    private fun thenResultsContainSpaghetti(recipe: RecipeDetails){
        var spaghettiFound = false;
        assertNotNull(recipe)
        if (recipe.name == "Spaghetti Bolognese"){
            spaghettiFound = true;
        }
        assertTrue(spaghettiFound)
    }

    @Test
    fun searchForGarbage_returnsNothing(){
        givenAFeedOfRecipeDataAvailable()
        runBlocking {
            launch(Dispatchers.IO){
                var detail = recipeService.fetchRecipeDetails("512312541")
                thenIGetNoResults(detail)
            }
        }
    }

    @Test
    fun getIngredientsForSpaghetti_returnList(){
        givenAFeedOfRecipeDataAvailable()
        runBlocking {
            launch(Dispatchers.IO){
                var detail = recipeService.fetchRecipeDetails("52770")//id for spaghetti bolognese
                assertTrue(detail.getIngredients().size > 0)
            }
        }
    }


    private fun thenIGetNoResults(detail: RecipeDetails) {
        assertEquals(null, detail.recipeID)
    }

}