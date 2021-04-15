package com.main.mealplanner

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.JsonDeserializer
import com.main.mealplanner.dto.MealPlan
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MealPlanViewModel : ViewModel(){
    lateinit var firestore : FirebaseFirestore
    var storageReferenence = FirebaseStorage.getInstance().getReference()
    val mealplans = MutableLiveData<ArrayList<MealPlan>>().apply{postValue(ArrayList<MealPlan>())}
    init{
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun save(
        mealplan: MealPlan
        //user: FirebaseUser*
    ) {
        val document =
            if (mealplan.MealPlanId != null && !mealplan.MealPlanId!!.isEmpty()) {
                // updating existing
                firestore.collection("mealplans").document(mealplan.MealPlanId!!)
            } else {
                // create new
                firestore.collection("mealplans").document()
            }
        mealplan.MealPlanId = document.id
        val set = document.set(object{
            val mealPlanID = mealplan.MealPlanId
            val ownerEmail = mealplan.OwnerEmail
            val recipeID = mealplan.RecipeId
            val cookSchedule = mealplan.CookSchedule?.format(DateTimeFormatter.ISO_DATE_TIME)
        })
        set.addOnSuccessListener {
            Log.d("Firebase", "document saved")
            mealplan.OwnerEmail?.let { it1 -> getMealPlans(it1) }
        }
    }

    fun getMealPlans(email: String){
        if(email == null || email == "") {
            mealplans.value = ArrayList<MealPlan>()
            return
        }
        val document = firestore.collection("mealplans").whereEqualTo("ownerEmail", email)
        document.get()
            .addOnSuccessListener {
                doc ->
                if(doc != null){
                    var mealPlans = ArrayList<MealPlan>()
                    doc.documents.forEach{
                        d ->
                        var mealPlan = MealPlan()

                        var cookTimeStr = d.get("cookSchedule") as? String
                        var cookTime = LocalDateTime.parse(cookTimeStr, DateTimeFormatter.ISO_DATE_TIME)
                        mealPlan.CookSchedule = cookTime
                        mealPlan.MealPlanId = d.get("mealPlanID") as? String
                        mealPlan.OwnerEmail = d.get("ownerEmail") as? String
                        mealPlan.RecipeId = d.get("recipeID") as? String
                        mealPlans.add(mealPlan)
                    }
                    mealplans.postValue(mealPlans)
                }


            }
    }

    fun getDate(date: HashMap<String, Any>){

    }

}