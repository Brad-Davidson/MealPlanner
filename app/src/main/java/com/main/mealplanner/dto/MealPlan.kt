package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "mealplan")
data class MealPlan(
    var cookSchedule: Date,
    var recipeID: String,
    var ownerEmail: String,
    @PrimaryKey @SerializedName("id") var mealPlanId: Int = 0
) {
    override fun toString(): String {
        return "Cook Recipe #$recipeID at $cookSchedule"
    }
}
