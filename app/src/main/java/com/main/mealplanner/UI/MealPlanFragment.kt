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

import com.main.mealplanner.MealPlanViewModel
import com.main.mealplanner.R
import kotlinx.android.synthetic.main.mealplan_fragment.*

class MealPlanFragment: Fragment(){
    companion object {
        fun newInstance() = MealPlanFragment()
    }

    private lateinit var mealPlanViewModel: MealPlanViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.mealplan_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mealPlanViewModel = ViewModelProvider(requireActivity()).get(MealPlanViewModel::class.java)
        mealPlanViewModel.mealplans.observe(viewLifecycleOwner, Observer{
            mealplans ->
            lstMealPlans.adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, mealplans)
        })

    }

}

