package com.main.mealplanner.UI

import android.app.usage.UsageEvents
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.main.mealplanner.MainViewModel
import androidx.lifecycle.ViewModelProviders
import com.main.mealplanner.R
import com.main.mealplanner.dto.RecipeDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.ArrayList

open class DetailsFragment: Fragment(){
    internal lateinit var viewModel: MainViewModel
    private var recipeDtl = RecipeDetails()

    companion object {
        const val RECIPE_ID = "recipe_id"

        fun newInstance(recipeID: String): DetailsFragment{
            val bundle = Bundle().apply {
                putString(RECIPE_ID, recipeID)
            }
            val fragment = DetailsFragment()
            fragment.arguments = bundle

            //this absolutely need to be removed and the call become asynchronous. however, unit tests don't work with async call, please advice
            var policy =  StrictMode.ThreadPolicy.Builder().permitAll().build()

            StrictMode.setThreadPolicy(policy)
            return fragment
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            viewModel = ViewModelProviders.of(it!!).get(MainViewModel::class.java)
        }

        recipeDtl = arguments?.getString("recipe_id")?.let { viewModel.fetchRecipe(it).first() }!!
        txtTitle?.setText(recipeDtl?.name)
        txtInstructions?.setText(recipeDtl?.instructions)


        Picasso.get().load(recipeDtl.recipeImageUrl).into(imgRecipe)
    }
}