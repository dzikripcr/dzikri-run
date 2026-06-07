package com.example.dzikri_run.Warga

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dzikri_run.data.AppDatabase
import com.example.dzikri_run.data.entity.WargaEntity
import com.example.dzikri_run.databinding.FragmentWargaBinding
import kotlinx.coroutines.launch

class WargaFragment : Fragment() {

    private var _binding: FragmentWargaBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: WargaAdapter
    private lateinit var db: AppDatabase
    private val wargaList = mutableListOf<WargaEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWargaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())

        // Inisialisasi adapter menggunakan LinearLayoutManager (list ke bawah biasa)
        adapter = WargaAdapter(wargaList, this) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Anda memilih Warga: ${selectedItem.nama}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvWarga.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = this@WargaFragment.adapter
        }

        fetchWarga()

        binding.fabAddWarga.setOnClickListener {
            startActivity(Intent(requireContext(), WargaFormActivity::class.java))
        }
    }

    private fun fetchWarga() {
        lifecycleScope.launch {
            val data = db.wargaDao().getAllWarga()
            wargaList.clear()
            wargaList.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    fun deleteWarga(warga: WargaEntity) {
        lifecycleScope.launch {
            db.wargaDao().delete(warga)
            fetchWarga()
            Toast.makeText(requireContext(), "Data warga dihapus", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchWarga()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}