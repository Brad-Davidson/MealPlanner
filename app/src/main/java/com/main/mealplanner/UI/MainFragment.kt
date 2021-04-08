package com.main.mealplanner.UI

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
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
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import com.main.mealplanner.*
import com.main.mealplanner.dto.MealPlan
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.service.NotificationService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.rowlayout.*
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
    lateinit var adapter: RecipeAdapter
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
        //viewModel.fetchAllRecipes()
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


        btnSchedule.setOnClickListener{
            (activity as MainActivity?)!!.openMealPlans()
        }

        btnCatagories.setOnClickListener{
            val intent = Intent(context!!, NotificationReceiver::class.java)
            intent.putExtra("Notification Text", "someText")
            val pendingIntent = PendingIntent.getBroadcast(context!!, 1234, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmMgr = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmMgr.set(AlarmManager.RTC_WAKEUP, 10000, pendingIntent)

        }

        btnShoppingList.setOnClickListener{
            (activity as MainActivity?)!!.openShoppingList()
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
        private var btnViewRecipe: Button = itemView.findViewById(R.id.btnViewRecipe)
        private var btnAddRecipe: Button = itemView.findViewById(R.id.btnAddRecipe)



        fun updateRecipes (recipe : RecipeHeader) {
            btnViewRecipe.setOnClickListener{
                openDetails(recipe)
            }

            btnAddRecipe.setOnClickListener{
                //mealPlanModel.addMeal(MealPlan(LocalDateTime.now(), recipe.recipeID, "Local", ""))
                mealPlanModel.save(MealPlan(LocalDateTime.now(), recipe.recipeID, "Local", ""))
            }
            lblRecipeInfo.text = recipe.toString()
            Picasso.get().load(recipe.recipeImageUrl).into(imgRecipeThumbnail)
        }

        private fun openDetails(recipe: RecipeHeader){
            (activity as MainActivity?)!!.openRecipeDetails(recipe.recipeID)
        }
    }
}

