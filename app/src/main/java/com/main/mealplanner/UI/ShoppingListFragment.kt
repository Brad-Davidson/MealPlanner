package com.main.mealplanner.UI

import android.content.Context
import android.graphics.Typeface
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
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.main.mealplanner.MainViewModel

import com.main.mealplanner.MealPlanViewModel
import com.main.mealplanner.R
import com.main.mealplanner.dto.Ingredient
import com.main.mealplanner.dto.MealPlan
import kotlinx.android.synthetic.main.mealplan_fragment.*
import kotlinx.android.synthetic.main.shoppinglist_fragment.*
import kotlinx.coroutines.launch

class ShoppingListFragment: Fragment(){
        companion object {
                fun newInstance(): ShoppingListFragment{
                        val fragment = ShoppingListFragment()
                        var policy =  StrictMode.ThreadPolicy.Builder().permitAll().build()

                        StrictMode.setThreadPolicy(policy)
                        return fragment
                }

        }

        private lateinit var mealPlanViewModel: MealPlanViewModel
        private lateinit var viewModel: MainViewModel
        private var ingredientList = HashMap<String, ArrayList<String>>()

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {
        return inflater.inflate(R.layout.shoppinglist_fragment, container, false)
        }
        override fun onActivityCreated(savedInstanceState: Bundle?) {
                super.onActivityCreated(savedInstanceState)
                mealPlanViewModel = ViewModelProvider(requireActivity()).get(MealPlanViewModel::class.java)
                viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

                //filter out the meal plans by email. if no email is supplied, no mealplan should be returned
                if(FirebaseAuth.getInstance().currentUser != null){
                        mealPlanViewModel.getMealPlans(FirebaseAuth.getInstance().currentUser!!.email)
                }
                else{
                        Toast.makeText(requireActivity(), "You are not logged in, please log in to use the scheduler", Toast.LENGTH_SHORT).show()
                }

                var continueObserving = true
                //observe the mealplans list if it updates, remake the list.
                mealPlanViewModel.mealplans.observe(viewLifecycleOwner, Observer { mealplans ->
                        viewLifecycleOwner.lifecycleScope.launch{
                                //I am doing this because of concurency errors I was getting.
                                //While this might be bad practice, I know that from the shopping list page, the mealplans cannot change.
                                if(continueObserving && mealplans.size > 0){
                                        continueObserving = false
                                        getShoppingList(mealplans)
                                        lstShoppingList?.setAdapter(ExpandableListAdapter(context!!, ArrayList(ingredientList.keys), ingredientList))
                                }
                        }

               })

        }

        /*
        Format the list of meals into a hashmap of type HashMap<IngredientName, ArrayList<Ingredients>>.
         */
        suspend fun getShoppingList(meals: ArrayList<MealPlan>){
                //for each meal, get its recipe's details and parse through the ingredient list.
                meals.forEach{
                        var recipeDetails = it.RecipeId?.let { it1 -> viewModel.fetchRecipe(it1) }
                        if(recipeDetails != null){
                                var recipeIngredients = recipeDetails.getIngredients()
                                for (ingredient in recipeIngredients) {
                                        //if the ingredient is already in the list, add to the quantity. otherwise, create a key for it.
                                        if(ingredientList.keys.contains(ingredient.name)){
                                                var shoppingListStr = ingredient.shoppingListString(recipeDetails.name.toString())
                                                ingredientList.get(ingredient.name)?.add(shoppingListStr)
                                        } else{
                                                var shoppingListStr = ingredient.shoppingListString(recipeDetails.name.toString())
                                                var ingredientArray = ArrayList<String>()
                                                ingredientArray.add(shoppingListStr)
                                                ingredient.name?.let { ingredientName -> ingredientList.put(ingredientName, ingredientArray)}
                                        }
                                }

                        }
                }
        }

        /*
        I got this class from https://www.tutorialspoint.com/how-to-create-a-expandable-listview-using-kotlin as a generic expandable list adapter
         */
        inner class ExpandableListAdapter internal constructor(
                private val context: Context,
                private val titleList: List<String>,
                private val dataList: HashMap<String, ArrayList<String>>
        ) : BaseExpandableListAdapter(){

                override fun getGroup(listPosition: Int): Any {
                        return this.titleList[listPosition]
                }

                override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
                        return true
                }

                override fun hasStableIds(): Boolean {
                        return false
                }

                override fun getGroupView(
                        listPosition: Int,
                        isExpanded: Boolean,
                        convertView: View?,
                        parent: ViewGroup
                ): View {
                        var convertView = convertView
                        val listTitle = getGroup(listPosition) as String
                        if (convertView == null) {
                                val layoutInflater =
                                        this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                                convertView = layoutInflater.inflate(R.layout.shoppinglist_item, null)
                        }
                        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.txtItem)
                        listTitleTextView.setTypeface(null, Typeface.BOLD)
                        listTitleTextView.text = listTitle
                        return convertView
                }

                override fun getChildrenCount(listPosition: Int): Int {
                        return this.dataList[this.titleList[listPosition]]!!.size
                }

                override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
                        return this.dataList[this.titleList[listPosition]]!![expandedListPosition]
                }

                override fun getGroupId(listPosition: Int): Long {
                        return listPosition.toLong()
                }

                override fun getChildView(
                        listPosition: Int,
                        expandedListPosition: Int,
                        isLastChild: Boolean,
                        convertView: View?,
                        parent: ViewGroup
                ): View {
                        var convertView = convertView
                        val expandedListText = getChild(listPosition, expandedListPosition) as String
                        if (convertView == null) {
                                val layoutInflater =
                                        this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                                convertView = layoutInflater.inflate(R.layout.shoppinglist_item, null)
                        }
                        val expandedListTextView = convertView!!.findViewById<TextView>(R.id.txtItem)
                        expandedListTextView.text = expandedListText
                        return convertView
                }

                override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
                        return expandedListPosition.toLong()
                }

                override fun getGroupCount(): Int {
                        return this.titleList.size
                }

        }
}

