package com.main.mealplanner

import android.app.*
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.main.mealplanner.UI.DetailsFragment
import com.main.mealplanner.UI.MainFragment
import com.main.mealplanner.UI.MealPlanFragment
import com.main.mealplanner.UI.ShoppingListFragment
import kotlinx.android.synthetic.main.shoppinglist_fragment.*
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var mainFragment: MainFragment
    private var notificationManager: NotificationManager? = null
    lateinit var textView: TextView
    lateinit var button: Button
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        mainFragment = MainFragment.newInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainFragment = MainFragment.newInstance()
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.btnPick)
        button.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                    DatePickerDialog(this@MainActivity, this@MainActivity, year, month,day)
            datePickerDialog.show()
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,mainFragment)
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logoutbutton){

        }
        if(item.itemId == R.id.btnShoppingListHeader){
            openShoppingList()
        }
        if(item.itemId == R.id.btnScheduleHeader){
            openMealPlans()
        }
        if(item.itemId == R.id.btnHome){
            openHomePage()
        }
        return super.onOptionsItemSelected(item)
    }

    fun openRecipeDetails(recipeID: String){
        var detailsFragment = DetailsFragment.newInstance(recipeID)
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, detailsFragment)
                .addToBackStack("tag")
                .commit()
    }

    fun openMealPlans(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MealPlanFragment.newInstance())
                .addToBackStack("tag")
                .commit()
    }
    fun openShoppingList(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShoppingListFragment.newInstance())
                .addToBackStack("tag")
                .commit()
    }
    fun openHomePage(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .addToBackStack("tag")
                .commit()
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = day
        myYear = year
        myMonth = month
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this@MainActivity, this@MainActivity, hour, minute,
                DateFormat.is24HourFormat(this))
        timePickerDialog.show()
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        textView.text = "Year: " + myYear + "\n" + "Month: " + myMonth + "\n" + "Day: " + myDay + "\n" + "Hour: " + myHour + "\n" + "Minute: " + myMinute
    }
}



