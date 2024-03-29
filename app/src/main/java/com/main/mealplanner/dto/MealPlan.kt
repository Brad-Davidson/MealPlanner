package com.main.mealplanner.dto

import java.security.InvalidParameterException
import java.time.LocalDateTime

data class MealPlan(var CookSchedule: LocalDateTime? = null
                    , var RecipeId : String? = ""
                    , var OwnerEmail : String? = ""
                    ,  var MealPlanId:String? = "") {
    constructor(): this(null, "", "", ""){
    }
    override fun toString(): String {
        return "Cook Recipe #$RecipeId at $CookSchedule"
    }

    /*
    Set the time for the meal plan. if the time was in the past (give or take a minute), then throw an InvalidParameterException
     */
    fun setTime(date: LocalDateTime) {
        val currentDate = LocalDateTime.now().minusMinutes(1)
        if (date.isBefore(currentDate)){
            throw InvalidParameterException()
        }
        else{
            CookSchedule = date
        }
    }
}
