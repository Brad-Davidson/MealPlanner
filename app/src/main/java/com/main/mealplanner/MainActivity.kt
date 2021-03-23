package com.main.mealplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import com.main.mealplanner.UI.MainFragment
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
                .replace(R.id.container,mainFragment)
                .commitNow()
        }

    }
}