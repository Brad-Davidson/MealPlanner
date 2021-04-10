package com.main.mealplanner.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.mealplanner.MainActivity
import com.main.mealplanner.MainViewModel

import com.main.mealplanner.MealPlanViewModel
import com.main.mealplanner.R
import com.main.mealplanner.dto.MealPlan
import com.main.mealplanner.dto.RecipeHeader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.mealplan_fragment.*
import kotlinx.android.synthetic.main.mealplanlayout.*
import java.time.LocalDateTime
import java.util.*

class MealPlanFragment: Fragment(){
    companion object {
        fun newInstance() = MealPlanFragment()
    }

    private lateinit var mealPlanViewModel: MealPlanViewModel
    private lateinit var viewModel: MainViewModel
    lateinit var adapter: MealPlanFragment.MealPlanAdapter
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.mealplan_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mealPlanViewModel = ViewModelProvider(requireActivity()).get(MealPlanViewModel::class.java)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        if(viewModel.user != null){
            mealPlanViewModel.getMealPlans(viewModel.user!!.email)
        }
        else{
            mealPlanViewModel.getMealPlans("")
        }

        lstMealPlan.hasFixedSize()
        lstMealPlan.layoutManager = LinearLayoutManager(context)
        lstMealPlan.itemAnimator = DefaultItemAnimator()
        adapter =MealPlanAdapter(R.layout.mealplanlayout)
        lstMealPlan.adapter = adapter
        mealPlanViewModel.mealplans.observe(this, Observer{
            mealplans ->
            lstMealPlan.adapter!!.notifyDataSetChanged()
        })
    }

    inner class MealPlanAdapter(val itemLayout: Int) : RecyclerView.Adapter<MealPlanViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPlanViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
            return MealPlanViewHolder(view)
        }

        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        override fun getItemCount(): Int {
            if(mealPlanViewModel.mealplans.value != null){
                return mealPlanViewModel.mealplans.value!!.size
            }
            else{
                return 0
            }
        }


        override fun onBindViewHolder(holder: MealPlanViewHolder, position: Int) {
            val mealPlan = mealPlanViewModel.mealplans.value?.get(position)
            if (mealPlan != null) {
                holder.updateMealPlans(mealPlan)
            }
        }


    }

    inner class MealPlanViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {



        fun updateMealPlans (mealPlan : MealPlan) {

        }

    }

}

