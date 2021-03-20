package com.main.mealplanner.UI

import android.app.usage.UsageEvents
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.main.mealplanner.MainViewModel
import androidx.lifecycle.ViewModelProviders
import com.main.mealplanner.R
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.ArrayList

class DetailsFragment: Fragment(){
    companion object {
        const val RECIPE_ID = ""

        fun newInstance(recipeID: String): DetailsFragment{
            val bundle = Bundle().apply {
                putString(RECIPE_ID, recipeID)
            }
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }
}