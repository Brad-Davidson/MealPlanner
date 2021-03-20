package com.main.mealplanner.UI

import android.app.usage.UsageEvents
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.main.mealplanner.MainViewModel
import androidx.lifecycle.ViewModelProviders
import com.main.mealplanner.R
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.ArrayList

class MainFragment: Fragment(){
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _events = ArrayList<UsageEvents.Event>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.fetchAllRecipes()
        viewModel.recipes.observe(this, Observer {
            recipes ->
                rcpSearch.setAdapter(
                    ArrayAdapter(
                        context!!,
                        android.R.layout.simple_spinner_dropdown_item,
                        recipes
                    )
                )

                lstRecipes.adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, recipes)

        })

        lstRecipes.onItemClickListener = object: AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val element = viewModel.recipes.value?.get(position)
                val detailFragment = element?.recipeID?.let { DetailsFragment.newInstance(it) }
                if (savedInstanceState == null) {
                    if (detailFragment != null) {
                        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main, detailFragment, "detailFragment")?.commit()
                    }
                }

            }

        }

    }
}