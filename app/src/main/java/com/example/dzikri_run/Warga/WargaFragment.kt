package com.example.dzikri_run.Warga

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dzikri_run.data.AppDatabase
import com.example.dzikri_run.data.entity.WargaEntity
import com.example.dzikri_run.databinding.FragmentWargaBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch


class WargaFragment : Fragment() {
    private var _binding: FragmentWargaBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: WargaAdapter
    private lateinit var db: AppDatabase
    private val wargaList = mutableListOf<WargaEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWargaBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view,savedInstanceState)
        db = AppDatabase.getInstance(requireContext())
        adapter = WargaAdapter(
            wargaList,
            onClick = {
                Toast.makeText(
                    requireContext(),
                    "Pilih ${it.nama}",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onDelete = {
                showDeleteDialog(it)
            }
        )
        binding.rvWarga.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@WargaFragment.adapter
        }
        binding.fabAddWarga.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    WargaFormActivity::class.java
                )
            )
        }
        fetchWarga()
    }
    private fun fetchWarga(){
        lifecycleScope.launch {
            val data = db.wargaDao().getAllWarga()
            adapter.updateData(data)
        }
    }
    private fun showDeleteDialog(
        warga: WargaEntity
    ){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Hapus Data")
            .setMessage(
                "Hapus data ${warga.nama}?"
            )
            .setPositiveButton("Ya"){ _,_ ->
                lifecycleScope.launch {
                    db.wargaDao().delete(warga)
                    adapter.removeItem(warga)
                    Toast.makeText(
                        requireContext(),
                        "Data berhasil dihapus",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .setNegativeButton("Batal",null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        fetchWarga()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}