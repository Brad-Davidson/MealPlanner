package com.main.mealplanner.dao

import androidx.room.*
import com.main.mealplanner.dto.RecipeDetailList
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.dto.RecipeList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

@Dao
interface IRecipeDAO {

    @GET("filter.php?i=")
    fun getAllRecipes() : Call<RecipeList>

    @GET("lookup.php")
    fun getRecipeDetails(@Query("i") i: String): Call<RecipeDetailList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipeHeaders: ArrayList<RecipeHeader>)

    @Delete
    fun delete(recipeHeader: RecipeHeader)

    @Insert
    fun save(recipeHeader: RecipeHeader)
}
