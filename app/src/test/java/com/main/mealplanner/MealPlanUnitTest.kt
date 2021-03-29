package com.main.mealplanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.main.mealplanner.dto.MealPlan
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