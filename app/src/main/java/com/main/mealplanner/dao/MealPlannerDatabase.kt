package com.main.mealplanner.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.main.mealplanner.dto.MealPlan

@Database(entities = arrayOf(MealPlan::class), version = 1)
abstract class MealPlannerDatabase : RoomDatabase() {
    abstract fun mealPlanDAO(): IMealPlanDAO
}
