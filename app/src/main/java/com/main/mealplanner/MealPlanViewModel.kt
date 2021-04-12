package com.main.mealplanner

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import com.main.mealplanner.dto.MealPlan
import java.time.LocalDateTime

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
        val set = document.set(mealplan)
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
                        //mealPlan.CookSchedule = document.get("cookSchedule") as LocalDateTime
                        mealPlan.MealPlanId = d.get("mealPlanId") as? String
                        mealPlan.OwnerEmail = d.get("ownerEmail") as? String
                        mealPlan.RecipeId = d.get("recipeId") as? String
                        mealPlans.add(mealPlan)
                    }
                    mealplans.postValue(mealPlans)
                }


            }
    }
}