package com.example.easybreakfast.ui.main

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easybreakfast.R
import com.example.easybreakfast.databinding.MainFragmentBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = MainFragmentBinding.inflate(inflater)
        binding.drawerButton.setOnClickListener {
            activity?.drawerLayout?.openDrawer(GravityCompat.START)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.context = activity
        recipeRecyclerList.layoutManager=LinearLayoutManager(activity)
        viewModel.recipeList.observe(this, Observer {recipeRecyclerList.adapter = RecipeAdapter(activity as Context, viewModel.recipeList.value!!)})
        search_button.setOnClickListener {
            viewModel.getRecipes(search_field.text.toString() )
            hideInput(search_field)
        }
        search_field.setOnEditorActionListener { _, actionId, _ ->
            when (actionId){
                EditorInfo.IME_ACTION_DONE -> {
                    viewModel.getRecipes(search_field.text.toString())
                    hideInput(search_field)
                    true
                }
                else ->{false}
            }
        }


        }

    private fun hideInput(view: View){
        val imm = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

