package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="ingredient")
data class Ingredient(var NearStore: String, var Quantity : Int, var Name : String, var Amounts: ArrayList<String>, @PrimaryKey @SerializedName("id") var IngredientId:Int = 0) {
    override fun toString(): String {
        return Name
    }
}
