package com.main.mealplanner.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.main.mealplanner.dto.Ingredient

@Dao
interface IIngredientDAO {

    @Query("SELECT * FROM Ingredient")
    fun getAllIngredients()  : LiveData<List<Ingredient>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(ingredients: ArrayList<Ingredient>)

    @Delete
    fun delete(ingredient: Ingredient)

    @Insert
    fun save(ingredient: Ingredient)
}
