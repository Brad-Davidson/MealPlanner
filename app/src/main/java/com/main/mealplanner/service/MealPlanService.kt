package com.main.mealplanner.service

import android.app.Application

class
MealPlanService(application: Application) {
    // TODO() Take out / Replace commented Code
//    private val application = application
//
//    internal suspend fun fetchMealPlans(MealPlanId: String) {
//        withContext(Dispatchers.IO) {
//            val service = RetrofitClientInstance.retrofitInstance?.create(IMealPlanDAO::class.java)
//            val mealplans = async { service?.getAllMealPlans() }
//
//            this.updateMealPlans(mealplans.await())
//        }
//    }
//
//    /**
//     * Store these mealplans locally, so that we can use the data without network latency
//     */
//    private suspend fun updateMealPlans(mealplans: ArrayList<MealPlan>?) {
//        var sizeOfMealPlans = mealplans?.size
//        try {
//            var MealPlanDAO = getMealPlanDAO()
//            MealPlanDAO.insertAll(mealplans!!)
//        } catch (e: Exception) {
//            e.message?.let { Log.e(TAG, it) }
//        }
//
//    }
//
//    internal fun getMealPlanDAO(): IMealPlanDAO {
//        val db = Room.databaseBuilder(application, MealPlanDatabase::class.java, "mymealplans").build()
//        val MealPlanDAO = db.MealPlanDAO()
//        return MealPlanDAO
//        }
//
//        internal fun save(mealplan: MealPlan) {
//            getMealPlanDAO().save(mealplan)
//        }
//    }
}



