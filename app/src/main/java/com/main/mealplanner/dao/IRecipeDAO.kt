package com.main.mealplanner.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.dto.RecipeList
import retrofit2.Call
import retrofit2.http.GET

@Dao
interface IRecipeDAO {

    @GET("filter.php?i=")
    fun getAllRecipes(): Call<RecipeList>


    //@Query("SELECT * FROM Recipe")
    //fun getAllRecipes()  : LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipeHeaders: ArrayList<RecipeHeader>)

    @Delete
    fun delete(recipeHeader: RecipeHeader)

    @Insert
    fun save(recipeHeader: RecipeHeader)
}
