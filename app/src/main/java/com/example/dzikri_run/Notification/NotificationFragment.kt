package com.example.dzikri_run.Notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dzikri_run.R
import com.example.dzikri_run.databinding.FragmentNotificationBinding
import com.example.dzikri_run.databinding.FragmentProfileBinding

class NotificationFragment : Fragment() {

    private var _binding: FragmentNotificationBinding? = null

    private val binding get() = _binding!!

    private val notifList = listOf(
        NotifModel(
            "Alya Putri",
            "Halo, jangan lupa meeting jam 10 ya 😊",
            "https://i.pravatar.cc/150?img=1"
        ),
        NotifModel(
            "Budi Santoso",
            "Tugas Android Studio sudah dikumpulkan?",
            "https://i.pravatar.cc/150?img=2"
        ),
        NotifModel(
            "Citra Dewi",
            "Aku sudah kirim file revisinya",
            "https://i.pravatar.cc/150?img=3"
        ),
        NotifModel(
            "Dika Pratama",
            "Besok kita presentasi bareng ya",
            "https://i.pravatar.cc/150?img=4"
        ),
        NotifModel(
            "Eka Saputra",
            "Mantap hasil desain UI kamu 🔥",
            "https://i.pravatar.cc/150?img=5"
        ),
        NotifModel(
            "Fajar Ramadhan",
            "Sudah sampai kampus belum?",
            "https://i.pravatar.cc/150?img=6"
        ),
        NotifModel(
            "Gita Lestari",
            "Tolong cek email terbaru ya",
            "https://i.pravatar.cc/150?img=7"
        ),
        NotifModel(
            "Hana Maharani",
            "Jangan lupa backup project nya",
            "https://i.pravatar.cc/150?img=8"
        ),
        NotifModel(
            "Irfan Hidayat",
            "Oke siap, nanti aku bantu",
            "https://i.pravatar.cc/150?img=9"
        ),
        NotifModel(
            "Joko Wijaya",
            "Sampai jumpa di kelas besok 👋",
            "https://i.pravatar.cc/150?img=10"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_notification, container, false)

        /** Ganti menjadi versi binding */
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Notificaion"
        }

        val adapter = NotifAdapter(requireContext(), notifList)
        binding.listNotifItems.adapter = adapter

//        /* Definisikan adapter sebagai penghubung dataList dengan layout simple_list_item_1 */
//        val adapter = ArrayAdapter(
//            requireContext(),
//            android.R.layout.simple_list_item_1,
//            notifList
//        )

        // Hubungkan listViewItems dengan adapter
        binding.listNotifItems.adapter = adapter

        // Tambahkan aksi saat item di-list diklik
        binding.listNotifItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = notifList[position]
            Toast.makeText(requireContext(), "Kamu memilih: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }
}