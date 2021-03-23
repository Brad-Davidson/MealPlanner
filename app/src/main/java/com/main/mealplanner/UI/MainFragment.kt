package com.main.mealplanner.UI

import android.app.AlertDialog
import android.app.usage.UsageEvents
import android.content.ContentValues
import android.content.DialogInterface
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.main.mealplanner.MainViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.mealplanner.R
import com.main.mealplanner.dto.RecipeHeader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.rowlayout.*
import java.util.ArrayList

class MainFragment: Fragment(){
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _recipes = ArrayList<RecipeHeader>()
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
        lstRecipes.hasFixedSize()
        lstRecipes.layoutManager = LinearLayoutManager(context)
        lstRecipes.itemAnimator = DefaultItemAnimator()
        lstRecipes.adapter = RecipeAdapter(_recipes, R.layout.rowlayout)

        viewModel.recipes.observe(this, Observer{
            recipes ->
            _recipes.removeAll(_recipes)
            _recipes.addAll(recipes)
            lstRecipes.adapter!!.notifyDataSetChanged()
        })
    }

    inner class RecipeAdapter(val recipes: List<RecipeHeader>, val itemLayout: Int) : RecyclerView.Adapter<RecipeViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
            return RecipeViewHolder(view)
        }

        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        override fun getItemCount(): Int {
            return recipes.size
        }

        override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
            val recipe = recipes.get(position)
            holder.updateRecipes(recipe)
        }


    }

    inner class RecipeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private var imgRecipeThumbnail : ImageView = itemView.findViewById(R.id.imgRecipe)
        private var lblRecipeInfo: TextView = itemView.findViewById(R.id.lblRecipeInfo)
        //tap event to open detail fragment
        fun updateRecipes (recipe : RecipeHeader) {
            lblRecipeInfo.text = recipe.toString()
            Picasso.get().load(recipe.recipeImageUrl).into(imgRecipeThumbnail)
        }
    }
}