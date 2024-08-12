package com.example.finalproject.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.DatabaseModel.Models.Meal
import com.example.finalproject.R
import com.example.finalproject.Screens.FavoritesFragmentDirections

class FavoritesAdapter(
    val navController: NavController,
    private val onDeleteClick: (Meal) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteRecipeViewHolder>() {

    private var favoriteRecipes: List<Meal> = listOf()

    class FavoriteRecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeDescriptionTextView: TextView = view.findViewById(R.id.recipe_description)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
        val imagemod: ImageView = view.findViewById(R.id.fav_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return FavoriteRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteRecipeViewHolder, position: Int) {
        val recipe = favoriteRecipes[position]
        holder.recipeDescriptionTextView.text = recipe.strMeal

        // Load image using Glide or another image loading library
        Glide.with(holder.itemView.context)
            .load(recipe.strMealThumb)
            .into(holder.imagemod)
        holder.itemView.setOnClickListener {
            val position = position
            val name = recipe.idMeal
            val action = FavoritesFragmentDirections.actionFavoriteFragmentToRecipeDetailsFragment(name,position.toString())
            navController.navigate(action)

        }

        holder.deleteButton.setOnClickListener {
            onDeleteClick(recipe)
        }
    }

    override fun getItemCount(): Int {
        return favoriteRecipes.size
    }

    fun updateRecipes(newRecipes: List<Meal>) {
        favoriteRecipes = newRecipes
        notifyDataSetChanged()
    }
}