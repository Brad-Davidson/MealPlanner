package com.main.mealplanner.service

import androidx.lifecycle.MutableLiveData
import com.main.mealplanner.RetrofitClientInstance
import com.main.mealplanner.dao.IRecipeDAO
import com.main.mealplanner.dto.RecipeHeader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import com.main.mealplanner.dto.RecipeDetailList
import com.main.mealplanner.dto.RecipeDetails
import com.main.mealplanner.dto.RecipeList
import com.main.mealplanner.dto.Ingredient
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RecipeService {
    fun fetchRecipeHeaders() : MutableLiveData<ArrayList<RecipeHeader>> {
        var _recipes = MutableLiveData<ArrayList<RecipeHeader>>()
        var _ingredients = MutableLiveData<ArrayList<Ingredient>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(IRecipeDAO::class.java)
        val call = service?.getAllRecipes()
        call?.enqueue(object: Callback<RecipeList> {
            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(
                    call: Call<RecipeList>,
                    response: Response<RecipeList>
            ) {
                _recipes.value = response.body()?.meals
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                Log.d("Error, ", t.message.toString())
            }

        })

        return _recipes
    }

    suspend fun fetchRecipeDetails(recipeID: String) : RecipeDetails{
        var _recipeDetails = RecipeDetails()
        var service = RetrofitClientInstance.retrofitInstance?.create(IRecipeDAO::class.java)
        var call = service?.getRecipeDetails(recipeID)
        val res = CompletableDeferred<RecipeDetails>()
        CoroutineScope(Dispatchers.IO).launch {
            call?.enqueue(object : Callback<RecipeDetailList> {
                /**
                 * Invoked for a received HTTP response.
                 *
                 *
                 * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                 * Call [Response.isSuccessful] to determine if the response indicates success.
                 */
                override fun onResponse(
                    call: Call<RecipeDetailList>,
                    response: Response<RecipeDetailList>
                ) {
                    if(response.body()?.meals != null){
                        _recipeDetails = response.body()?.meals!!.first()
                    }
                    res.complete(_recipeDetails)
                }

                /**
                 * Invoked when a network exception occurred talking to the server or when an unexpected
                 * exception occurred creating the request or processing the response.
                 */
                override fun onFailure(call: Call<RecipeDetailList>, t: Throwable) {
                    Log.d("Error, ", t.message.toString())
                    res.completeExceptionally(t)
                }

            })
        }

        return res.await()
    }

}
