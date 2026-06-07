package com.example.dzikri_run.Warga

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
import com.example.dzikri_run.data.entity.WargaEntity
import com.example.dzikri_run.databinding.ActivityWargaFormBinding
import kotlinx.coroutines.launch

class WargaFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWargaFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWargaFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tambah Data Warga"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back) // Sesuaikan icon back kamu
        }

        db = AppDatabase.getInstance(this)

        binding.btnSaveWarga.setOnClickListener {
            val nik = binding.etNik.text.toString()
            val nama = binding.etNama.text.toString()
            val jenisKelamin = binding.etJenisKelamin.text.toString()
            val noHp = binding.etNoHp.text.toString()

            if (nik.isNotBlank() && nama.isNotBlank() && jenisKelamin.isNotBlank() && noHp.isNotBlank()) {
                lifecycleScope.launch {
                    val wargaBaru = WargaEntity(
                        nik = nik,
                        nama = nama,
                        jenisKelamin = jenisKelamin,
                        noHp = noHp
                    )
                    db.wargaDao().insert(wargaBaru)
                    Toast.makeText(this@WargaFormActivity, "Data warga berhasil disimpan!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
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