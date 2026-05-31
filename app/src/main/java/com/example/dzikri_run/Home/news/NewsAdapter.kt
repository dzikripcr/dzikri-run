package com.example.dzikri_run.Home.news

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dzikri_run.R
import com.example.dzikri_run.data.model.NewsModel
import com.example.dzikri_run.databinding.ItemNewsBinding

class NewsAdapter(
    private var items: List<NewsModel>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    fun setData(newItems: List<NewsModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(
        val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {

        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {

        val item = items[position]

        holder.binding.txtTitle.text = item.title

        // Format tanggal dari isoDate
        holder.binding.txtDate.text =
            if (item.isoDate.length >= 10) {
                item.isoDate.substring(0, 10)
            } else {
                item.isoDate
            }

        // Load gambar dari API baru
        Glide.with(holder.itemView.context)
            .load(item.image?.large ?: item.image?.small)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .into(holder.binding.imgNews)

        holder.itemView.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(item.link)
            )

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}