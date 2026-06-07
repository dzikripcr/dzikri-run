package com.example.dzikri_run.Home.pertemuan10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dzikri_run.Notification.NotificationFragment
import com.example.dzikri_run.Warga.WargaFragment

class TenthTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Jumlah total tab yang ada
    override fun getItemCount(): Int = 3

    // Menentukan Fragment mana yang akan ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabDataProyekFragment()
            1 -> WargaFragment()
            2 -> NotificationFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}