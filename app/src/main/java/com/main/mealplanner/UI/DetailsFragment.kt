package com.main.mealplanner.UI

import android.os.Bundle
import android.os.StrictMode
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.main.mealplanner.MainViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.main.mealplanner.R
import com.main.mealplanner.dto.RecipeDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


open class DetailsFragment: Fragment(){
    private lateinit var viewModel: MainViewModel
    private var recipeDetails = RecipeDetails()

    companion object {
        const val RECIPE_ID = "recipe_id"

        fun newInstance(recipeID: String): DetailsFragment{
            val bundle = Bundle().apply {
                putString(RECIPE_ID, recipeID)
            }
            val fragment = DetailsFragment()
            fragment.arguments = bundle

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
        lifecycleScope.launch{

            //wait for the recipe details. once it is received, fill in everything on the page
            val results = async{recipeDetails = arguments?.getString("recipe_id")?.let { viewModel.fetchRecipe(it) }!!}
            results.await()
            txtTitle?.text = recipeDetails?.name
            txtInstructions?.text = recipeDetails?.instructions
            txtInstructions.movementMethod = ScrollingMovementMethod.getInstance()
            lstIngredients.adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, recipeDetails?.getIngredients())

            Picasso.get().load(recipeDetails.recipeImageUrl).into(imgRecipe)
        }

    }
}