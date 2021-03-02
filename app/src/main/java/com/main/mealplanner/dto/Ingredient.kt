package com.main.mealplanner.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="ingredient")
data class Ingredient(var nearStore: String, var quantity : Int, var name : String, @PrimaryKey @SerializedName("id") var ingredientId:Int = 0) {
    override fun toString(): String {
        return name
    }
}
