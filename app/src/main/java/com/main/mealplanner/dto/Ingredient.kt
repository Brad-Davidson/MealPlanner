package com.main.mealplanner.dto

/*
Class to combine an ingredient and its quantity.
 */
data class Ingredient(var nearStore: String, var quantity: String?, var name: String?) {
    override fun toString(): String {
        return "$name: $quantity"
    }

    /*
    Returns a formatted string for the shopping list fragment
     */
    fun shoppingListString(recipeName: String): String{
        return "$quantity for $recipeName"
    }
}
