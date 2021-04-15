package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.security.InvalidParameterException
import java.security.acl.Owner
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class MealPlan(var CookSchedule: LocalDateTime? = null
                    , var RecipeId : String? = ""
                    , var OwnerEmail : String? = ""
                    ,  var MealPlanId:String? = "") {
    constructor(): this(LocalDateTime.now(), "", "", ""){
    }
    override fun toString(): String {
        return "Cook Recipe #$RecipeId at $CookSchedule"
    }

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
