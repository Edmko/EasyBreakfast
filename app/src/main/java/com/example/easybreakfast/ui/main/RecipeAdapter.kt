package com.example.easybreakfast.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easybreakfast.R
import com.example.easybreakfast.model.Hit
import kotlinx.android.synthetic.main.recipe_card.view.*

class RecipeAdapter(private val context: Context, private val data: List<Hit>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        var ingredients =""
        data[position].recipe.ingredients.forEach { ingredients += it.text.substringBefore(",")+"\n" }
        holder.title.text = data[position].recipe.label.substringBefore(",")
        Glide.with(context).load(data[position].recipe.image).into(holder.image)
        holder.description.text = ingredients
        holder.recipeCard.setOnClickListener {view: View ->
            val action = MainFragmentDirections.actionMainFragmentToWebViewFragment2(data[position].recipe.url)
            view.findNavController().navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.recipe_card, parent, false)
        )
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.recipe_image!!
        val title = itemView.title!!
        val description = itemView.description!!
        val recipeCard = itemView.recipeCard
    }
}