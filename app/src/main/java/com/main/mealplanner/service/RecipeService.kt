package com.main.mealplanner.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.main.mealplanner.RetrofitClientInstance
import com.main.mealplanner.dao.IRecipeDAO
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.dto.RecipeList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeService {
    fun fetchRecipe(meal: String): MutableLiveData<ArrayList<RecipeHeader>> {
        var _recipes = MutableLiveData<ArrayList<RecipeHeader>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(IRecipeDAO::class.java)
        val call = service?.getAllRecipes()
        call?.enqueue(object : Callback<RecipeList> {
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
                Log.d("RecipeService Error", t.message.toString())
            }

        })

        return _recipes
    }

}