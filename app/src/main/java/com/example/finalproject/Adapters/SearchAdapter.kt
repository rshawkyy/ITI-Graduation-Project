package com.example.finalproject.Adapters

import RandomMealResponse
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.Screens.SearchFragmentDirections

class SearchAdapter (private val context: Context, val navController: NavController) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {
    lateinit var allData: RandomMealResponse

    fun submitData(newAllData: RandomMealResponse) {
        allData = newAllData
        notifyDataSetChanged()
    }

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val title: TextView = row.findViewById(R.id.tv_fav_meal_name)
        val img: ImageView = row.findViewById(R.id.img_fav_meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.fav_meal_card, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = allData.meals.get(position)
        holder.title.text = category.strMeal

        Glide.with(context)
            .load(category.strMealThumb)
            .centerCrop()
            .into(holder.img)
        holder.itemView.setOnClickListener {
            val position = position
            val name = category.idMeal
            val action = SearchFragmentDirections.actionSearchFragmentToRecipeDetailFragment(name,position.toString())
            navController.navigate(action)

        }
    }


    override fun getItemCount(): Int {
        return allData.meals.size
    }
}