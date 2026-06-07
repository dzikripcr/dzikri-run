package com.example.dzikri_run.Warga

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dzikri_run.data.entity.WargaEntity
import com.example.dzikri_run.databinding.ItemWargaBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class WargaAdapter(
    private val wargaList: List<WargaEntity>,
    private val wargaFragment: WargaFragment, // Referensi ke Fragment untuk hapus data
    private val onItemClick: (WargaEntity) -> Unit
) : RecyclerView.Adapter<WargaAdapter.WargaViewHolder>() {

    inner class WargaViewHolder(val binding: ItemWargaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WargaViewHolder {
        val binding = ItemWargaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WargaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WargaViewHolder, position: Int) {
        val item = wargaList[position]

        with(holder.binding) {
            tvNamaWarga.text = item.nama
            tvNikWarga.text = "NIK: ${item.nik}"
            tvJkWarga.text = "JK: ${item.jenisKelamin}"
            tvNoHpWarga.text = "HP: ${item.noHp}"

            root.setOnClickListener {
                onItemClick(item)
            }

            btnDeleteWarga.setOnClickListener {
                MaterialAlertDialogBuilder(holder.itemView.context)
                    .setTitle("Hapus Data Warga")
                    .setMessage("Apakah Anda yakin ingin menghapus data atas nama ${item.nama}?")
                    .setPositiveButton("Ya") { dialog, _ ->
                        wargaFragment.deleteWarga(item)
                        dialog.dismiss()
                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    override fun getItemCount(): Int = wargaList.size
}