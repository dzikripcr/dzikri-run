package com.example.dzikri_run.Warga

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dzikri_run.data.entity.WargaEntity
import com.example.dzikri_run.databinding.ItemWargaBinding

class WargaAdapter(
    private val wargaList: MutableList<WargaEntity>,
    private val onClick: (WargaEntity) -> Unit,
    private val onDelete: (WargaEntity) -> Unit
) : RecyclerView.Adapter<WargaAdapter.WargaViewHolder>() {
    inner class WargaViewHolder(
        private val binding: ItemWargaBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WargaEntity) {
            binding.apply {
                tvNamaWarga.text = item.nama
                tvNikWarga.text = "NIK: ${item.nik}"
                tvJkWarga.text = "JK: ${item.jenisKelamin}"
                tvNoHpWarga.text = "HP: ${item.noHp}"
                root.setOnClickListener {
                    onClick(item)
                }
                btnDeleteWarga.setOnClickListener {
                    onDelete(item)
                }
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WargaViewHolder {
        val binding = ItemWargaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WargaViewHolder(binding)
    }
    override fun onBindViewHolder(
        holder: WargaViewHolder,
        position: Int
    ) {
        holder.bind(wargaList[position])
    }
    override fun getItemCount(): Int {
        return wargaList.size
    }
    fun updateData(data: List<WargaEntity>) {
        wargaList.clear()
        wargaList.addAll(data)
        notifyDataSetChanged()
    }
    fun removeItem(item: WargaEntity) {
        val index = wargaList.indexOf(item)
        if(index != -1){
            wargaList.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}