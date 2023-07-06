package com.mfdsix.astedroid.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfdsix.astedroid.core.R
import com.mfdsix.astedroid.core.databinding.ItemFavoritedAsteroidBinding
import com.mfdsix.astedroid.core.domain.model.Asteroid

class FavoritedAsteroidAdapter () : RecyclerView.Adapter<FavoritedAsteroidAdapter.ListViewHolder>() {

    private var listData = ArrayList<Asteroid>()
    var onItemClick: ((Asteroid) -> Unit)? = null

    fun setData(newListData: List<Asteroid>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorited_asteroid, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavoritedAsteroidBinding.bind(itemView)
        fun bind(data: Asteroid) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image ?: ContextCompat.getDrawable(itemView.context, R.drawable.asteroid))
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemDescription.text = data.description.substring(0, 50)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}