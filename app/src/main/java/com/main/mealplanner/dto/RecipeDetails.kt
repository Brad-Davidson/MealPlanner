package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="recipedetails")
data class RecipeDetails(var NearStore: String, var Quantity : Int, var Name : String, @PrimaryKey @SerializedName("id") var RecipeDetailsId:Int = 0) {
    override fun toString(): String {
        return Name
    }
}
