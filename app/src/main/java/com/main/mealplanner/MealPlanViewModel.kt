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

class MealPlanViewModel : ViewModel(){
    lateinit var firestore : FirebaseFirestore
    var storageReferenence = FirebaseStorage.getInstance().getReference()

    init{
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }
    val mealplans = MutableLiveData<ArrayList<MealPlan>>().apply{postValue(ArrayList<MealPlan>())}
    fun save(
        mealplan: MealPlan
        //user: FirebaseUser
    ) {
        val document =
            if (mealplan.MealPlanId != null && !mealplan.MealPlanId.isEmpty()) {
                // updating existing
                firestore.collection("mealplans").document(mealplan.MealPlanId)
            } else {
                // create new
                firestore.collection("mealplans").document()
            }
        mealplan.MealPlanId = document.id
        val set = document.set(mealplan)
        set.addOnSuccessListener {
            Log.d("Firebase", "document saved")
            getMealPlans(mealplan.OwnerEmail)
        }
    }

    fun getMealPlans(email: String){
        val document = firestore.collection("mealplans")
        document.get()
            .addOnSuccessListener {
                doc ->
                if(doc != null){
                    val mealplanResults = doc.toObjects<MealPlan>()
                    mealplans.value = mealplanResults as ArrayList<MealPlan>
                }
            }
    }
}