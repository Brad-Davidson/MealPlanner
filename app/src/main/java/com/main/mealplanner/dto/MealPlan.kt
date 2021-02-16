package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="mealplan")
data class MealPlan(var CookSchedule: DateTime, var RecipeId : String, @PrimaryKey @SerializedName("id") var MealPlanId:Int = 0) {
    override fun toString(): String {
        return CookSchedule
    }
