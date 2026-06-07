package com.example.dzikri_run.Home.pertemuan10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dzikri_run.data.entity.ProyekEntity
import com.example.dzikri_run.databinding.ItemProyekBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProyekAdapter(
    private val proyekList: List<ProyekEntity>,
    private val proyekFragment: TabDataProyekFragment,
    private val onItemClick: (ProyekEntity) -> Unit
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

            // Klik pada seluruh card/item
            root.setOnClickListener {
                onItemClick(item)
            }

            btnDelete.setOnClickListener {
                MaterialAlertDialogBuilder(holder.itemView.context)
                    .setTitle("Hapus Proyek")
                    .setMessage("Apakah kamu yakin ingin menghapus proyek ini?")
                    .setPositiveButton("Ya") { dialog, _ ->

                        // Panggil fungsi delete di Fragment
                        proyekFragment.deleteProyek(item)
                        dialog.dismiss()

                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    override fun getItemCount(): Int = proyekList.size
}