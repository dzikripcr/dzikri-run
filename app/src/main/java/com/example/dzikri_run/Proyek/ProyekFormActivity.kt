package com.example.dzikri_run.Proyek

import android.Manifest
import android.icu.util.Calendar
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.dzikri_run.R
import com.example.dzikri_run.data.AppDatabase
import com.example.dzikri_run.data.entity.ProyekEntity
import com.example.dzikri_run.databinding.ActivityProyekFormBinding
import com.example.dzikri_run.utils.NotificationHelper
import com.example.dzikri_run.utils.PermissionHelper
import com.example.dzikri_run.utils.ReminderHelper
import kotlinx.coroutines.launch

class ProyekFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProyekFormBinding
    private lateinit var db: AppDatabase // Deklarasi database

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProyekFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tambah Data Proyek"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }

        /** Inisialisasi DB **/
        db = AppDatabase.getInstance(this)

        /** Aksi ketika tombol simpan diklik **/
        binding.btnSaveProyek.setOnClickListener {
            // Mengambil text dari inputan user
            val nama = binding.etNamaProyek.text.toString()
            val anggaran = binding.etAnggaranProyek.text.toString()
            val gambar = binding.etGambarProyek.text.toString()

            // Mengecek apakah ada field yang kosong
            if (nama.isNotBlank() && anggaran.isNotBlank() && gambar.isNotBlank()) {

                /** Penggunaan Coroutine untuk proses insert data di Background Thread **/
                lifecycleScope.launch {
                    // Membuat objek entitas proyek baru
                    val proyekBaru = ProyekEntity(
                        namaProyek = nama,
                        anggaranProyek = anggaran,
                        gambarProyek = gambar
                    )

                    // Memasukkan data ke dalam database
                    db.proyekDao().insert(proyekBaru)

                    // Menutup form dan kembali ke fragment sebelumnya
                    finish()
                }
            } else {
                // Menampilkan peringatan jika form belum lengkap
                Toast.makeText(this, "Mohon isi semua kolom data!", Toast.LENGTH_SHORT).show()
            }

//            NotificationHelper.showNotification(
//                this,
//                "Notifikasi",
//                "Halo $nama, Berhasil ditambahkan!",
//                intent
//            )

            val calendar = Calendar.getInstance().apply {
                add(Calendar.MINUTE, 1) // Tambah 1 menit dari sekarang
            }

            ReminderHelper.setReminder(
                context = this,
                hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE),
                title = "Reminder 1 Menit",
                message = "Halo $nama, reminder ini muncul 1 menit setelah tombol ditekan",
                targetActivity = ProyekFormActivity::class.java
            )
            Toast.makeText(this, "Silahkan tunggu 1 Menit untuk menerima Notifikasi...", Toast.LENGTH_SHORT).show()
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