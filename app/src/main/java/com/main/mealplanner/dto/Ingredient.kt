package com.main.mealplanner.dto

data class Ingredient(var nearStore: String, var quantity: String?, var name: String?) {
    override fun toString(): String {
        return "$name: $quantity"
    }

    fun shoppingListString(recipeName: String): String{
        return "$quantity for $recipeName"
    }
}
