package com.example.finalproject.Adapters

import MealDetail
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.Screens.HomeFragmentDirections

class RandomImageAdapter (private val context: Context, val navController: NavController) : RecyclerView.Adapter<RandomImageAdapter.ImageViewHolder>() {

    private var imageList: List<MealDetail> = emptyList()

    fun setImageList(meals: List<MealDetail>) {
        imageList = meals
        notifyDataSetChanged()
    }



    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image_view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.designhomescroll, parent, false)
        return ImageViewHolder(layout)
    }


    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val meal = imageList[position]
        Glide.with(context)
            .load(meal.strMealThumb)
            .centerCrop()
            .into(holder.image)
        holder.itemView.setOnClickListener {
            val position = position
            val name = meal.idMeal
            val action = HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(name,position.toString())
            navController.navigate(action)

        }
    }
}