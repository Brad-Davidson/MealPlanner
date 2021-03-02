package com.main.mealplanner.UI

import android.app.usage.UsageEvents
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.main.mealplanner.MainViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.main.mealplanner.R
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.ArrayList

class MainFragment: Fragment(){

    private lateinit var viewModel: MainViewModel
    private var _events = ArrayList<UsageEvents.Event>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.fetchAllRecipes()

        observeRecipies()
    }

   private fun observeRecipies(){
        viewModel.recipes.observe(this, Observer {
                recipes ->
            rcpSearch.setAdapter(
                ArrayAdapter(
                    context!!,
                    android.R.layout.simple_spinner_dropdown_item,
                    recipes
                )
            )
            lstRecipes.adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, recipes)

        })
    }
}