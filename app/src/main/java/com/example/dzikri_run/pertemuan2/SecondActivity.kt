package com.example.dzikri_run.pertemuan2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzikri_run.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ===== PERSEGI =====
        val etSisiPersegi = findViewById<EditText>(R.id.etSisiPersegi)
        val btnHitungPersegi = findViewById<Button>(R.id.btnHitungPersegi)
        val tvHasilPersegi = findViewById<TextView>(R.id.tvHasilPersegi)

        btnHitungPersegi.setOnClickListener {
            val sisi = etSisiPersegi.text.toString()

            if (sisi.isNotEmpty()) {
                val s = sisi.toDouble()
                val luas = s * s
                tvHasilPersegi.text = "Hasil: $luas"
            } else {
                tvHasilPersegi.text = "Masukkan nilai sisi!"
            }
        }

        // ===== KUBUS =====
        val etSisiKubus = findViewById<EditText>(R.id.etSisiKubus)
        val btnHitungKubus = findViewById<Button>(R.id.btnHitungKubus)
        val tvHasilKubus = findViewById<TextView>(R.id.tvHasilKubus)

        btnHitungKubus.setOnClickListener {
            val sisi = etSisiKubus.text.toString()

            if (sisi.isNotEmpty()) {
                val s = sisi.toDouble()
                val volume = s * s * s
                tvHasilKubus.text = "Hasil: $volume"
            } else {
                tvHasilKubus.text = "Masukkan nilai sisi!"
            }
        }
    }
}