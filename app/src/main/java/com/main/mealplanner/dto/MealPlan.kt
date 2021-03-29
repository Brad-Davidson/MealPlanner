package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.security.InvalidParameterException
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(tableName="mealplan")
data class MealPlan(var CookSchedule: LocalDateTime, var RecipeId : String, var OwnerEmail : String, @PrimaryKey @SerializedName("id") var MealPlanId:Int = 0) {
    override fun toString(): String {
        return "Cook Recipe #$RecipeId at $CookSchedule"
    }

    fun setTime(date: LocalDateTime) {
        val currentDate = LocalDateTime.now()
        if (date.isBefore(currentDate)){
            throw InvalidParameterException()
        }
        else{
            CookSchedule = date
        }
    }
}
