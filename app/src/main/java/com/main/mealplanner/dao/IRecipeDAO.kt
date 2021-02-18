package com.main.mealplanner.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.main.mealplanner.dto.Recipe

@Dao
interface IRecipeDAO {

    @Query("SELECT * FROM Recipe")
    fun getAllRecipes()  : LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipes: ArrayList<Recipe>)

    @Delete
    fun delete(recipe: Recipe)

    @Insert
    fun save(recipe: Recipe)
}
