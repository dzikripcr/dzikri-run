package com.example.dzikri_run.Home.pertemuan10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dzikri_run.Home.pertemuan13.TabCaptureFragment
import com.example.dzikri_run.Home.pertemuan13.TabQrcodeFragment
import com.example.dzikri_run.Home.pertemuan13.TabScanFragment
import com.example.dzikri_run.Notification.NotificationFragment
import com.example.dzikri_run.Warga.WargaFragment

class TenthTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Jumlah total tab yang ada
    override fun getItemCount(): Int = 6

    // Menentukan Fragment mana yang akan ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabDataProyekFragment()
            1 -> WargaFragment()
            2 -> NotificationFragment()
            3 -> TabCaptureFragment()
            4 -> TabQrcodeFragment()
            5 -> TabScanFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}