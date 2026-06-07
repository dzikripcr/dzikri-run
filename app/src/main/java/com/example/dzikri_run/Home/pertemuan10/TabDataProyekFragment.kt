package com.example.dzikri_run.Home.pertemuan10

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dzikri_run.Proyek.ProyekFormActivity
import com.example.dzikri_run.data.AppDatabase
import com.example.dzikri_run.data.entity.ProyekEntity
import com.example.dzikri_run.databinding.FragmentTabDataProyekBinding
import kotlinx.coroutines.launch

class TabDataProyekFragment : Fragment() {

    private var _binding: FragmentTabDataProyekBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProyekAdapter
    private lateinit var db: AppDatabase
    private val proyekList = mutableListOf<ProyekEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabDataProyekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())

        adapter = ProyekAdapter(proyekList, this) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Anda memilih ${selectedItem.namaProyek}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvProyek.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            this.adapter = this@TabDataProyekFragment.adapter
        }

        fetchProyek()

        binding.fabAddProyek.setOnClickListener {
            startActivity(Intent(requireContext(), ProyekFormActivity::class.java))
        }
    }

    private fun fetchProyek() {
        lifecycleScope.launch {
            val data = db.proyekDao().getAllProyek()
            proyekList.clear()
            proyekList.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    fun deleteProyek(proyek: ProyekEntity) {
        lifecycleScope.launch {
            db.proyekDao().delete(proyek) // Hapus dari Room Database
            fetchProyek()                 // Segarkan ulang data dari Database ke RecyclerView
            Toast.makeText(requireContext(), "Proyek dihapus", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchProyek()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}