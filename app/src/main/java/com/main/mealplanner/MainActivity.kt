package com.main.mealplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchAllRecipes()
    }
}