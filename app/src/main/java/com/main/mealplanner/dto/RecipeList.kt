package com.main.mealplanner.dto

import com.google.gson.annotations.SerializedName
/*
This class is only used to get results from the retrofit call to the mealdb api
 */
data class RecipeList(@SerializedName("meals") val meals: ArrayList<RecipeHeader>){

}