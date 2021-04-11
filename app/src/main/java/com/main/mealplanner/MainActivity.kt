package com.main.mealplanner

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.main.mealplanner.UI.DetailsFragment
import com.main.mealplanner.UI.MainFragment
import com.main.mealplanner.UI.MealPlanFragment
import com.main.mealplanner.UI.ShoppingListFragment
import kotlinx.android.synthetic.main.shoppinglist_fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment
    private var notificationManager: NotificationManager? = null

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
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