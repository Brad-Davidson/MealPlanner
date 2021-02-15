package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="recipe")
data class Plant(var Meal: String, var Country : String, var time : int, var Ingredients : String, var Instructions : String, @PrimaryKey @SerializedName("id") var RecipeId:Int = 0) {
    override fun toString(): String {
        return Meal
    }
}
