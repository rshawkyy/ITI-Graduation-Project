package com.example.finalproject.Screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Adapters.FavoritesAdapter
import com.example.finalproject.DatabaseModel.AllDatabase.Database.FavoriteDatabase
import com.example.finalproject.R
import com.example.finalproject.Repository.FavRecipeRepoImp
import com.example.finalproject.ViewModel.FavouriteRecipeVM
import com.example.finalproject.ViewModel.FavouriteRecipeVMFactory
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteRecipeAdapter: FavoritesAdapter
    private lateinit var viewModel: FavouriteRecipeVM
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        navController=findNavController()

        // Initialize the adapter with an empty list and a delete action
        favoriteRecipeAdapter = FavoritesAdapter (navController){ meal ->
            // Handle delete button click
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    viewModel.delete(meal)
                    Toast.makeText(requireContext(), "${meal.strMeal} removed from favorites", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Failed to remove from favorites: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        recyclerView.adapter = favoriteRecipeAdapter

        // Initialize the ViewModel
        getFavoriteViewModel()

        // Observe the LiveData from ViewModel
        viewModel.getAllFavoriteRecipes().observe(viewLifecycleOwner) { favoriteMeals ->
            // Update the adapter with the list of favorite recipes
            favoriteRecipeAdapter.updateRecipes(favoriteMeals)
        }

        return view
    }

    private fun getFavoriteViewModel() {
        val favoriteViewModelFactory = FavouriteRecipeVMFactory(
            FavRecipeRepoImp(
                FavoriteDatabase.getDatabase(requireContext()).favoriteRecipeDao()
            )
        )
        viewModel = ViewModelProvider(this, favoriteViewModelFactory).get(FavouriteRecipeVM::class.java)
    }
}