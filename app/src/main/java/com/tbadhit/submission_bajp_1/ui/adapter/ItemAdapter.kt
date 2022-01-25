package com.tbadhit.submission_bajp_1.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tbadhit.submission_bajp_1.data.ItemEntity
import com.tbadhit.submission_bajp_1.databinding.ItemsBinding
import com.tbadhit.submission_bajp_1.ui.detail.DetailActivity
import com.tbadhit.submission_bajp_1.ui.detail.DetailActivity.Companion.CLICK
import com.tbadhit.submission_bajp_1.ui.detail.DetailActivity.Companion.EXTRA_ITEM
import com.tbadhit.submission_bajp_1.utils.loadImage

class ItemAdapter(private val idAdapter: Int) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val listItem = ArrayList<ItemEntity>()

    fun setData(dataItems: List<ItemEntity>) {
        listItem.apply {
            clear()
            addAll(dataItems)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    inner class ItemViewHolder(private val binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemEntity) {
            with(binding) {
                tvTitleMovie.text = item.title
                tvGenre.text = item.genre
                tvDuration.text = item.duration
                tvRate.text = item.rate.toString()
                tvYear.text = item.releaseYear
                imgMovie.loadImage(item.imagePath)
            }
            onItemClick(itemView, item, itemView.context)
        }
    }

    private fun onItemClick(view: View, item: ItemEntity, context: Context) {
        view.setOnClickListener {
            val intent = Intent(
                context, DetailActivity::class.java
            ).apply {
                putExtra(EXTRA_ITEM, item.id)
                putExtra(CLICK, idAdapter)
            }
            context.startActivity(intent)
        }
    }
}