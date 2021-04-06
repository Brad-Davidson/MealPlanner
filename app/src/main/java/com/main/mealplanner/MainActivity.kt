package com.main.mealplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.main.mealplanner.UI.DetailsFragment
import com.main.mealplanner.UI.MainFragment
import com.main.mealplanner.UI.MealPlanFragment
import com.main.mealplanner.UI.ShoppingListFragment
import kotlinx.android.synthetic.main.shoppinglist_fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        mainFragment = MainFragment.newInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainFragment = MainFragment.newInstance()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,mainFragment)
                .commitNow()
        }
    }

    fun openRecipeDetails(recipeID: String){
        var detailsFragment = DetailsFragment.newInstance(recipeID)
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, detailsFragment)
                .addToBackStack("tag")
                .commit()
    }

    fun openMealPlans(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MealPlanFragment.newInstance())
                .addToBackStack("tag")
                .commit()
    }
    fun openShoppingList(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShoppingListFragment.newInstance())
                .addToBackStack("tag")
                .commit()
    }

}