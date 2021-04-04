package com.main.mealplanner.dto

import com.google.gson.annotations.SerializedName

data class RecipeDetailList(@SerializedName("meals") val meals: ArrayList<RecipeDetails>){

}