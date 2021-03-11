package com.main.mealplanner

import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.main.mealplanner.dto.RecipeHeader
import org.junit.Assert.*
import io.mockk.confirmVerified
import io.mockk.verify
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

    @Test confirmSpaghettiInstructions_outputsInstructions(){
        var spaghetti = RecipeDetails()
        spaghetti.instructions = "test"
        assertEquals("test", spaghetti.instructions)
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
                if (it.name == "Spaghetti"){
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
        mvm.fetchAllRecipes()
    }

    private fun thenIGetNoResults(){
        mvm.recipes.observeForever {
            assertEquals(0, it.size)
        }
    }

}