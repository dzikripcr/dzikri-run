package com.example.dzikri_run

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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