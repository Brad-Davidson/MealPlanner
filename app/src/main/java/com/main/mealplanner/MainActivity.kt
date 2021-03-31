package com.main.mealplanner

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.main.mealplanner.UI.DetailsFragment
import com.main.mealplanner.UI.MainFragment
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        mainFragment = MainFragment.newInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainFragment = MainFragment.newInstance()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, mainFragment)
                .commitNow()
        }

    }

    fun openRecipeDetails(recipeID: String){
        var detailsFragment = DetailsFragment.newInstance(recipeID)
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, detailsFragment)
                .commitNow()
    }

    fun goBack(view: View) {
        val intent = Intent(this@MainActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}