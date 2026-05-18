package com.example.dzikri_run.Home.pertemuan10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dzikri_run.databinding.ItemProyekBinding

class ProyekAdapter(
    private val proyekList: List<ProyekModel>,
    private val onItemClick: (ProyekModel) -> Unit
) : RecyclerView.Adapter<ProyekAdapter.ProyekViewHolder>() {

    inner class ProyekViewHolder(val binding: ItemProyekBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProyekViewHolder {
        val binding = ItemProyekBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProyekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProyekViewHolder, position: Int) {
        val item = proyekList[position]

        with(holder.binding) {
            tvNamaProyek.text = item.namaProyek
            tvAnggaranProyek.text = item.anggaranProyek

            Glide.with(holder.itemView.context)
                .load(item.gambarProyek)
                .into(imgProyek)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = proyekList.size
}