package com.main.mealplanner.UI

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.main.mealplanner.MainViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.main.mealplanner.MainActivity
import com.main.mealplanner.MealPlanViewModel
import com.main.mealplanner.R
import com.main.mealplanner.dto.MealPlan
import com.main.mealplanner.dto.RecipeHeader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_fragment.*
import java.time.LocalDateTime
import java.util.*

class MainFragment: Fragment(){
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mealPlanModel: MealPlanViewModel
    private var _recipes = ArrayList<RecipeHeader>() //this is a copy of the full data set
    private var _filteredRecipes = ArrayList<RecipeHeader>()
    private var _mealplans = ArrayList<MealPlan>()
    private val AUTH_REQUEST_CODE = 2002
    lateinit var adapter: RecipeAdapter
    private var pendingMealPlan: MealPlan = MealPlan()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mealPlanModel = ViewModelProvider(requireActivity()).get(MealPlanViewModel::class.java)
        viewModel.fetchAllRecipes()
        lstRecipes.hasFixedSize()
        lstRecipes.layoutManager = LinearLayoutManager(context)
        lstRecipes.itemAnimator = DefaultItemAnimator()
        adapter = RecipeAdapter(R.layout.rowlayout)
        lstRecipes.adapter = adapter

        rcpSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })

        viewModel.recipes.observe(this, Observer{
            recipes ->
            _recipes.removeAll(_recipes)
            _recipes.addAll(recipes)
            _filteredRecipes = _recipes
            lstRecipes.adapter!!.notifyDataSetChanged()
        })


    }

    private fun logon(mealPlan: MealPlan): FirebaseUser? {
        var providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        pendingMealPlan = mealPlan
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), AUTH_REQUEST_CODE
        )

        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        return user
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 2002) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                pendingMealPlan.OwnerEmail = FirebaseAuth.getInstance().currentUser.email
                if(pendingMealPlan.RecipeId != null && pendingMealPlan.RecipeId != ""){
                    mealPlanModel.save(pendingMealPlan)
                }
                pendingMealPlan = MealPlan()
            } else {
                Toast.makeText(context!!, "Error signing in", Toast.LENGTH_LONG)
            }
        }
    }

    inner class RecipeAdapter(val itemLayout: Int) : RecyclerView.Adapter<RecipeViewHolder>(), Filterable {
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
            return _filteredRecipes.size
        }

        override fun getFilter(): Filter {
            return object: Filter(){
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val charSearch = constraint.toString()
                    if (charSearch.isEmpty()) {
                        _filteredRecipes = _recipes
                    } else {
                        val resultList = ArrayList<RecipeHeader>()
                        for (row in _recipes) {
                            if (row.name.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                                resultList.add(row)
                            }
                        }
                        _filteredRecipes = resultList
                    }
                    val filterResults = FilterResults()
                    filterResults.values = _filteredRecipes
                    return filterResults
                }

                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    _filteredRecipes = results?.values as ArrayList<RecipeHeader>
                    notifyDataSetChanged()
                }

            }
        }

        override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
            val recipe = _filteredRecipes.get(position)
            holder.updateRecipes(recipe)
        }


    }

    inner class RecipeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private var imgRecipeThumbnail : ImageView = itemView.findViewById(R.id.imgRecipe)
        private var lblRecipeInfo: TextView = itemView.findViewById(R.id.lblRecipeInfo)
        private var btnViewRecipe: ImageButton = itemView.findViewById(R.id.btnViewRecipe)
        private var btnAddRecipe: ImageButton = itemView.findViewById(R.id.btnAddRecipe)



        fun updateRecipes (recipe : RecipeHeader) {
            btnViewRecipe.setOnClickListener{
                openDetails(recipe)
            }

            btnAddRecipe.setOnClickListener{

                if(FirebaseAuth.getInstance().currentUser == null){
                    logon(MealPlan(LocalDateTime.now(), recipe.recipeID, "", ""))
                }
                else if(FirebaseAuth.getInstance().currentUser != null){
                    mealPlanModel.save(MealPlan(null, recipe.recipeID, FirebaseAuth.getInstance().currentUser!!.email, ""))
                    Toast.makeText(context, "${recipe.name} added to your MealPlan", Toast.LENGTH_SHORT).show()
                }

            }
            lblRecipeInfo.text = recipe.toString()
            Picasso.get().load(recipe.recipeImageUrl).into(imgRecipeThumbnail)
        }

         fun openDetails(recipe: RecipeHeader){
            (activity as MainActivity?)!!.openRecipeDetails(recipe.recipeID)
        }

    }
}

