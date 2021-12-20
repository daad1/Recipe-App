package com.example.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.ItemRowBinding

class RecipeCard(title: String, author: String, ing: String, inst: String) {
    val title = title
    val author = author
    val ingredients = ing
    val instructions = inst
}
class RecyclerViewAdapter(private val container: ArrayList<RecipeCard>
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = container[position]
        holder.binding.apply {
            tvAuthor.text = item.author
            tvTitle.text = item.title
            tvIng.text = item.ingredients
            tvInst.text = item.instructions

            holder.itemView.setOnClickListener {
            }
        }


    }

    override fun getItemCount() = container.size
}