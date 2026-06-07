package com.example.dzikri_run.Proyek

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.dzikri_run.R
import com.example.dzikri_run.data.AppDatabase
import com.example.dzikri_run.data.entity.ProyekEntity
import com.example.dzikri_run.databinding.ActivityProyekFormBinding
import kotlinx.coroutines.launch

class ProyekFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProyekFormBinding
    private lateinit var db: AppDatabase // Deklarasi database

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