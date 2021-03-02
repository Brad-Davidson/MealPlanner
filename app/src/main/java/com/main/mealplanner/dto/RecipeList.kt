package com.main.mealplanner.dto

import com.google.gson.annotations.SerializedName

data class RecipeList(@SerializedName("meals") val meals: ArrayList<RecipeHeader>){

    override fun toString(): String {
        return "Total Meals In The Recipe List: ${meals.size}"
    }

}