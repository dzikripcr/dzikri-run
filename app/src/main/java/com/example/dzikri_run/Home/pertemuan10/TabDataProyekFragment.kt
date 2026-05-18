package com.example.dzikri_run.Home.pertemuan10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dzikri_run.R
import com.example.dzikri_run.databinding.FragmentHomeBinding
import com.example.dzikri_run.databinding.FragmentTabDataProyekBinding

class TabDataProyekFragment : Fragment() {

    private var _binding: FragmentTabDataProyekBinding? = null
    private val binding get() = _binding!!

    private val proyekList = listOf(

        ProyekModel(
            "Pembangunan Jalan Desa",
            "Rp 250.000.000",
            "https://picsum.photos/seed/desa1/400/300"
        ),

        ProyekModel(
            "Renovasi Balai Desa",
            "Rp 180.000.000",
            "https://picsum.photos/seed/desa2/400/300"
        ),

        ProyekModel(
            "Pembangunan Jembatan Dusun",
            "Rp 320.000.000",
            "https://picsum.photos/seed/desa3/400/300"
        ),

        ProyekModel(
            "Perbaikan Saluran Irigasi",
            "Rp 145.000.000",
            "https://picsum.photos/seed/desa4/400/300"
        ),

        ProyekModel(
            "Pembangunan Posyandu",
            "Rp 95.000.000",
            "https://picsum.photos/seed/desa5/400/300"
        ),

        ProyekModel(
            "Pengadaan Lampu Jalan",
            "Rp 110.000.000",
            "https://picsum.photos/seed/desa6/400/300"
        ),

        ProyekModel(
            "Pembangunan Taman Desa",
            "Rp 75.000.000",
            "https://picsum.photos/seed/desa7/400/300"
        ),

        ProyekModel(
            "Pembuatan Sumur Bor",
            "Rp 130.000.000",
            "https://picsum.photos/seed/desa8/400/300"
        ),

        ProyekModel(
            "Pembangunan Drainase",
            "Rp 210.000.000",
            "https://picsum.photos/seed/desa9/400/300"
        ),

        ProyekModel(
            "Renovasi Mushola Desa",
            "Rp 85.000.000",
            "https://picsum.photos/seed/desa10/400/300"
        ),

        ProyekModel(
            "Pembangunan Lapangan Futsal",
            "Rp 275.000.000",
            "https://picsum.photos/seed/desa11/400/300"
        ),

        ProyekModel(
            "Pembangunan Gedung PAUD",
            "Rp 165.000.000",
            "https://picsum.photos/seed/desa12/400/300"
        ),

        ProyekModel(
            "Pengadaan Internet Desa",
            "Rp 90.000.000",
            "https://picsum.photos/seed/desa13/400/300"
        ),

        ProyekModel(
            "Pembuatan Embung Desa",
            "Rp 400.000.000",
            "https://picsum.photos/seed/desa14/400/300"
        ),

        ProyekModel(
            "Perbaikan Jalan Tani",
            "Rp 155.000.000",
            "https://picsum.photos/seed/desa15/400/300"
        ),

        ProyekModel(
            "Pembangunan Tempat Sampah Terpadu",
            "Rp 120.000.000",
            "https://picsum.photos/seed/desa16/400/300"
        ),

        ProyekModel(
            "Renovasi Kantor Desa",
            "Rp 240.000.000",
            "https://picsum.photos/seed/desa17/400/300"
        ),

        ProyekModel(
            "Pembangunan Pasar Desa",
            "Rp 350.000.000",
            "https://picsum.photos/seed/desa18/400/300"
        ),

        ProyekModel(
            "Pembuatan Area Wisata Desa",
            "Rp 500.000.000",
            "https://picsum.photos/seed/desa19/400/300"
        ),

        ProyekModel(
            "Pembangunan Gudang UMKM",
            "Rp 175.000.000",
            "https://picsum.photos/seed/desa20/400/300"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tab_data_proyek, container, false)

        _binding = FragmentTabDataProyekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProyekAdapter(proyekList) { selectedItem ->

            Toast.makeText(
                requireContext(),
                "Anda memilih ${selectedItem.namaProyek}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvProyek.apply {

            /** Mode Grid **/
            layoutManager = GridLayoutManager(requireContext(), 2)

            /** Jika ingin model Linear **/
            // layoutManager = LinearLayoutManager(requireContext())

            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}