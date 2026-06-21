package com.example.dzikri_run.Home.pertemuan10

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzikri_run.R
import com.example.dzikri_run.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    companion object {
        const val EXTRA_OPEN_TAB = "open_tab"
        const val TAB_PROYEK = 0
        const val TAB_WARGA = 1
        const val TAB_NOTIFIKASI = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Layanan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }

        val tabsAdapter = TenthTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Proyek"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_proyek)
                }
                1 -> {
                    tab.text = "Warga"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_warga)
                }
                2 -> {
                    tab.text = "Notifikasi"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_notif)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 10
                }
            }
        }.attach()

        // Kalau Activity ini dibuka lewat klik notifikasi, pindah ke tab yang dituju
        handleOpenTabIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleOpenTabIntent(intent)
    }

    private fun handleOpenTabIntent(intent: Intent?) {
        val tabIndex = intent?.getIntExtra(EXTRA_OPEN_TAB, -1) ?: -1
        if (tabIndex in TAB_PROYEK..TAB_NOTIFIKASI) {
            binding.viewPager.post {
                binding.viewPager.currentItem = tabIndex
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}