package com.example.finalproject.Screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Adapters.RandomImageAdapter
import com.example.finalproject.Adapters.adapter
import com.example.finalproject.R
import com.example.finalproject.Repository.ProductRepoImp
import com.example.finalproject.Repository.RandomImageRepoImp
import com.example.finalproject.Retrofit.ApiClient
import com.example.finalproject.ViewModel.MVVM
import com.example.finalproject.ViewModel.RandomImageVM
import com.example.finalproject.ViewModel.RandomImageVMFactory
import com.example.finalproject.ViewModel.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var randomImageRecyclerView: RecyclerView
    private lateinit var adapter: adapter
    private lateinit var randomImageAdapter: RandomImageAdapter
    private lateinit var viewModel: MVVM
    private lateinit var randomImageViewModel: RandomImageVM
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recycl)
        randomImageRecyclerView = view.findViewById(R.id.imagerecycleview)
        navController=findNavController()

        recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        adapter = adapter(requireContext(),navController)
        recyclerView.adapter = adapter

        randomImageRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        randomImageAdapter = RandomImageAdapter(requireContext(),navController)
        randomImageRecyclerView.adapter = randomImageAdapter

        // Initialize ViewModels
        getViewModels()
        observeViewModels()

        return view
    }

    private fun getViewModels() {
        val apiViewModelFactory = ViewModelFactory(ProductRepoImp(ApiClient))
        viewModel = ViewModelProvider(this, apiViewModelFactory).get(MVVM::class.java)


        val randomImageRepository = RandomImageRepoImp(ApiClient)
        val randomImageViewModelFactory = RandomImageVMFactory(randomImageRepository)
        randomImageViewModel = ViewModelProvider(this, randomImageViewModelFactory).get(RandomImageVM::class.java)
    }

    private fun observeViewModels() {

        viewModel.categoryResponse.observe(viewLifecycleOwner) { categoryResponse ->
            if (categoryResponse != null) {
                adapter.setCategoryList(categoryResponse)
            } else {
                Log.e("HomeFragment", "No category data available")
            }
        }

        randomImageViewModel.randomImageResponse.observe(viewLifecycleOwner) { response ->
            val meals = response?.meals
            if (!meals.isNullOrEmpty()) {
                randomImageAdapter.setImageList(meals)
            } else {
                Log.e("HomeFragment", "No random meals found")
            }
        }

        // Fetch data from ViewModels
        viewModel.getAllCategories()
        randomImageViewModel.fetchRandomImages()
    }
}