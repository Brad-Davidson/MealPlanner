package com.main.mealplanner

import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.main.mealplanner.dto.RecipeDetails
import com.main.mealplanner.dto.RecipeHeader
import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

class RecipeUnitTests {


    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var mvm:MainViewModel

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
        var spaghetti = RecipeDetails("1234","","","Spaghetti", "","","","", "","","","", "","","","", "","","","", "","","","","","","","","","","","","","","","", "","","","", "","","","", "test","")
        spaghetti.instructions = "test"
        assertEquals("test", spaghetti.instructions)
    }

    @Test
    fun searchForSpaghetti_returnsSpaghetti(){
        givenAFeedOfRecipeDataAvailable()
        var detail = mvm.fetchRecipe("52770") //id for spaghetti bolognese
        thenResultsContainSpaghetti(detail)
    }
    private fun thenResultsContainSpaghetti(details: ArrayList<RecipeDetails>){
        var spaghettiFound = false;
        assertNotNull(details)
        assertTrue(details.size > 0)
        details.forEach {
            if (it.name == "Spaghetti Bolognese"){
                spaghettiFound = true;
            }
        }
        assertTrue(spaghettiFound)
    }

    @Test
    fun searchForGarbage_returnsNothing(){
        givenAFeedOfMockedRecipeDataAvailable()
        var detail = mvm.fetchRecipe("512312541")
        thenIGetNoResults(detail)
    }

    private fun givenAFeedOfMockedRecipeDataAvailable() {
        mvm.fetchAllRecipes()
    }

    private fun thenIGetNoResults(detail: ArrayList<RecipeDetails>) {
        assertEquals(0, detail.size)
    }

}