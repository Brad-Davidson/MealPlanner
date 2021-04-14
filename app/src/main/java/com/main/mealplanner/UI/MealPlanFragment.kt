package com.main.mealplanner.UI

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.StrictMode
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
import android.app.Notification
import android.app.TimePickerDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.work.*

import com.main.mealplanner.MealPlanViewModel
import com.main.mealplanner.R
import com.main.mealplanner.dto.MealPlan
import com.main.mealplanner.dto.RecipeHeader
import com.main.mealplanner.service.NotificationService
import com.main.mealplanner.service.RecipeService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.mealplan_fragment.*
import kotlinx.android.synthetic.main.mealplan_fragment.view.*
import kotlinx.android.synthetic.main.mealplanlayout.*
import kotlinx.coroutines.launch
import java.security.InvalidParameterException
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.Year
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit

class MealPlanFragment: Fragment(){
    companion object {
        fun newInstance(): MealPlanFragment{
            val fragment = MealPlanFragment()
            var policy =  StrictMode.ThreadPolicy.Builder().permitAll().build()

            StrictMode.setThreadPolicy(policy)
            return fragment
        }
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

    inner class MealPlanViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var lblRecipeName : TextView = itemView.findViewById(R.id.lblRecipeName)
        private var btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
        private var btnOpenTimePicker: Button = itemView.findViewById(R.id.btnOpenTimePicker)
        private var textView: TextView = itemView.findViewById(R.id.textView)
        private var btnViewRecipe: ImageButton = itemView.findViewById(R.id.btnViewRecipe)

        fun updateMealPlans (mealPlan : MealPlan) {
            lifecycleScope.launch{
                var recipeDetails = mealPlan.RecipeId?.let { it -> viewModel.fetchRecipe(it) }
                if (recipeDetails != null) {
                    lblRecipeName.text = recipeDetails.name
                    btnOpenTimePicker.text = mealPlan.CookSchedule.toString()
                }
            }

            btnOpenTimePicker.setOnClickListener{
                val currentDateTime = Calendar.getInstance()
                val startYear = currentDateTime.get(Calendar.YEAR)
                val startMonth = currentDateTime.get(Calendar.MONTH)
                val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
                val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
                val startMinute = currentDateTime.get(Calendar.MINUTE)
                DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(year, month, day, hour, minute)
                        pickedDateTime.set(Calendar.SECOND, 0)
                        var mealTime = LocalDateTime.ofInstant(pickedDateTime.toInstant(), pickedDateTime.timeZone.toZoneId())
                        try{
                            mealPlan.setTime(mealTime)
                            btnOpenTimePicker.text = mealTime.toString()
                            var timeDiff = LocalDateTime.from(LocalDateTime.now())
                            var secondsUntil = timeDiff.until(mealTime, ChronoUnit.SECONDS)
                            mealPlan.MealPlanId?.let { plan -> scheduleNotification(secondsUntil, plan, "You have a meal scheduled, open your MealPlanner to view details") }
                        }
                        catch(e: InvalidParameterException){
                            Toast.makeText(requireContext(), "Invalid Time, please pick a date + time in the future!", Toast.LENGTH_SHORT).show()
                        }

                    }, startHour, startMinute, false).show()
                }, startYear, startMonth, startDay).show()
            }

            }
        fun scheduleNotification(timeDelay: Long, tag: String, body: String) {

            val data = Data.Builder().putString("body", body)
            //cancel any work task for that meal plan
            WorkManager.getInstance().cancelAllWorkByTag(tag)

            val work = OneTimeWorkRequestBuilder<NotificationService>()
                    .setInitialDelay(timeDelay, TimeUnit.SECONDS)
                    .setInputData(data.build())
                    .addTag(tag)
                    .build()

            WorkManager.getInstance().enqueue(work)
        }



    }

}



