package com.main.mealplanner

import androidx.lifecycle.MutableLiveData
import com.main.mealplanner.dto.MealPlan
import com.main.mealplanner.dto.Recipe
import com.main.mealplanner.service.MealPlanService
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.security.InvalidParameterException
import java.util.*

class MealPlanUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var mvm:MainViewModel

    /*
    var mealPlanService = mockk<MealPlanService>();

    private fun createMockData(){
        var allMealPlanLiveData = MutableLiveData<ArrayList<MealPlan>>()
        var allMealPlans = ArrayList<MealPlan>()

        var mealPlan = MealPlan(Date(2000, 1, 1), "1", "Brad", 1 )
        allMealPlans.add((mealPlan))
        allMealPlanLiveData.postValue(allMealPlans)

        mvm.mealPlanService = mealPlanService
    }
     */

    @Test
    fun confirmMealPlan_outputsPlan(){
        var mealPlan = MealPlan(Date(2000, 1, 1), "1", "Brad", 1 )
        Assert.assertEquals("Cook Recipe # 1 at Thu Feb 01 00:00:00 UTC 3900", mealPlan.toString())
    }

    @Test(expected = InvalidParameterException::class)
    fun confirmMealPlan_setInvalidTime(){
        var mealPlan = MealPlan( Date(), "1", "Brad", 1 )
        mealPlan.setTime(Date(2000,1,1))
    }

    @Test
    fun confirmMealPlan_setTime(){
        var mealPlan = MealPlan( Date(), "1", "Brad", 1 )
        mealPlan.setTime(Date(2030, 1, 1))
    }

}