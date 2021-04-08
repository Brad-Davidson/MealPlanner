package com.main.mealplanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.main.mealplanner.dto.MealPlan
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.security.InvalidParameterException
import java.time.LocalDateTime

class MealPlanUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var mvm:MainViewModel

    @Test
    fun confirmMealPlan_outputsPlan(){
        var mealPlan = MealPlan(LocalDateTime.of(2000, 1, 1, 0, 0), "1", "Brad", "1" )
        Assert.assertEquals("Cook Recipe #1 at 2000-01-01T00:00", mealPlan.toString())
    }

    @Test(expected = InvalidParameterException::class)
    fun confirmMealPlan_setInvalidTime(){
        var mealPlan = MealPlan( LocalDateTime.now(), "1", "Brad", "1" )
        mealPlan.setTime(LocalDateTime.of(2000, 1, 1, 0, 0))
    }

    @Test
    fun confirmMealPlan_setTime(){
        var mealPlan = MealPlan( LocalDateTime.now(), "1", "Brad", "1" )
        mealPlan.setTime(LocalDateTime.of(2030, 1, 1, 0, 0))
    }

}