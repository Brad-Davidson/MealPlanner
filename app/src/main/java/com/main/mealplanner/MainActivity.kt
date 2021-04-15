package com.main.mealplanner

import android.app.*
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.firebase.ui.auth.AuthUI
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.main.mealplanner.UI.DetailsFragment
import com.main.mealplanner.UI.MainFragment
import com.main.mealplanner.UI.MealPlanFragment
import com.main.mealplanner.UI.ShoppingListFragment
import kotlinx.android.synthetic.main.shoppinglist_fragment.*
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //logic for setting click events for the top menu buttons.
        if(item.itemId == R.id.logoutbutton){
            if(FirebaseAuth.getInstance().currentUser != null){
                Toast.makeText(this, "You have signed out", Toast.LENGTH_LONG).show()
                FirebaseAuth.getInstance().signOut()
            }
            else{
                var providers = arrayListOf(
                    AuthUI.IdpConfig.EmailBuilder().build()
                )
                startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), 2002
                )
            }
        }
        if(item.itemId == R.id.btnShoppingListHeader){
            openShoppingList()
        }
        if(item.itemId == R.id.btnScheduleHeader){
            openMealPlans()
        }
        if(item.itemId == R.id.btnHome){
            openHomePage()
        }
        return super.onOptionsItemSelected(item)
    }

    /*
    navigate to the details fragment.
    This fragment shows the ingredients and instrutions of a recipe
     */
    fun openRecipeDetails(recipeID: String){
        var detailsFragment = DetailsFragment.newInstance(recipeID)
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, detailsFragment)
                .addToBackStack("tag")
                .commit()
    }

    /*
    navigate to the meal plan scheduler fragment.
    This fragment is used to schedule meals for a specific time
     */
    fun openMealPlans(){

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MealPlanFragment.newInstance())
                .addToBackStack("tag")
                .commit()

    }

    /*
    opens the shopping list fragment.
    This fragment shows the ingredients and quantities of those ingredients for all scheduled recipes
     */
    fun openShoppingList(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShoppingListFragment.newInstance())
                .addToBackStack("tag")
                .commit()
    }

    /*
    Go back to the home screen
     */
    fun openHomePage(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .addToBackStack("tag")
                .commit()
    }
}



