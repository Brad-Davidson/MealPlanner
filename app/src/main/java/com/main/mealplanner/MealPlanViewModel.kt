package com.main.mealplanner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.main.mealplanner.dto.MealPlan

class MealPlanViewModel : ViewModel(){
    val mealplans = MutableLiveData<ArrayList<MealPlan>>().apply{postValue(ArrayList<MealPlan>())}
    fun addMeal(meal: MealPlan){
        var newPlan = mealplans.value
        newPlan!!.add(meal)
        mealplans.value = newPlan
    }
}