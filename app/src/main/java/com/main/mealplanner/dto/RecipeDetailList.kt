package com.main.mealplanner.dto

import com.google.gson.annotations.SerializedName
/*
This class is only used to get results from the mealdb api retrofit call
 */
data class RecipeDetailList(@SerializedName("meals") val meals: ArrayList<RecipeDetails>){

}