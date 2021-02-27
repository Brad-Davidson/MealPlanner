package com.main.mealplanner.service

import androidx.lifecycle.MutableLiveData
import com.main.mealplanner.RetrofitClientInstance
import com.main.mealplanner.dao.IRecipeDAO
import com.main.mealplanner.dto.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeService {
    fun fetchRecipe(meal: String) : MutableLiveData<ArrayList<Recipe>> {
        var _recipes = MutableLiveData<ArrayList<Recipe>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(IRecipeDAO::class.java)
        val call = service?.fetchAllRecipes()
        call?.enqueue(object: Callback<ArrayList<Recipe>> {
            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(
                call: Call<ArrayList<Recipe>>,
                response: Response<ArrayList<Recipe>>
            ) {
                _recipes.value = response.body()
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<ArrayList<Recipe>>, t: Throwable) {
                val j = 1 + 1
                val i = 1 + 1
            }

        })

        return _recipes
    }

}