package com.main.mealplanner.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.main.mealplanner.dto.MealPlan

@Dao
interface IMealPlanDAO {

    @Query("SELECT * FROM MealPlan")
    fun getAllMealPlans()  : LiveData<List<MealPlan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(mealPlan: ArrayList<MealPlan>)

    @Delete
    fun delete(mealPlan: MealPlan)

    @Insert
    fun save(mealPlan: MealPlan)
}
