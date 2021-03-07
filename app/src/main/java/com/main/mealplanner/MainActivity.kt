package com.main.mealplanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.main.mealplanner.UI.MainFragment

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
}