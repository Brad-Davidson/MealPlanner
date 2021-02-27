package com.main.mealplanner.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.main.mealplanner.dto.Recipe
import retrofit2.Call
import retrofit2.http.GET

@Dao
interface IRecipeDAO {

    @GET("json/v1/1/search.php?s=")
    fun getAllRecipes() : Call<ArrayList<Recipe>>


    //@Query("SELECT * FROM Recipe")
    //fun getAllRecipes()  : LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipes: ArrayList<Recipe>)

    @Delete
    fun delete(recipe: Recipe)

    @Insert
    fun save(recipe: Recipe)
}
