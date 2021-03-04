package com.main.mealplanner.dto

import com.google.gson.annotations.SerializedName

data class RecipeList(@SerializedName("meals") val meals: ArrayList<RecipeHeader>) {

    /***
     * overrides default toString method
     */
    override fun toString(): String {
        return "${meals.size} Meals loaded"
    }

}