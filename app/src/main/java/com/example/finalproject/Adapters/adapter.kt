package com.example.finalproject.Adapters

import CategoryResponse
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
import com.example.finalproject.Screens.HomeFragment
import com.example.finalproject.Screens.HomeFragmentDirections

class adapter (private val context: Context, val navController: NavController,) : RecyclerView.Adapter<adapter.MyViewHolder>() {

    private var data: CategoryResponse? = null

    fun setCategoryList(categoryList: CategoryResponse) {
        this.data = categoryList
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

    override fun getItemCount(): Int {
        return data?.categories?.size ?: 0    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = data?.categories?.get(position) ?: return

        holder.title.text = category.strCategory
        Glide.with(context)
            .load(category.strCategoryThumb)
            .centerCrop()
            .into(holder.img)
        holder.itemView.setOnClickListener {
            val name_category = data?.categories?.get(position)?.strCategory
            val action =
                HomeFragmentDirections.actionHomeFragmentToMealsFragment(name_category.toString())
            navController.navigate(action)

        }
    }


}