package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName="mealplan")
data class MealPlan(var CookSchedule: Date, var RecipeId : String, var OwnerEmail : String, @PrimaryKey @SerializedName("id") var MealPlanId:Int = 0) {
    override fun toString(): String {
        return "Cook Recipe # $RecipeId at $CookSchedule"
    }
}
