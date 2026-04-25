package com.example.dzikri_run.pertemuan4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzikri_run.R
import com.example.dzikri_run.databinding.ActivityDashboardBinding
import com.example.dzikri_run.pertemuan2.SecondActivity
import com.example.dzikri_run.pertemuan3.ThirdActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRumusBR.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.btnProductDetail.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

        binding.btnMyCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        //Kode ini harus selalu dipanggil saat butuh akses dengan nama "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnLogout.setOnClickListener {
            //alert dialog konfirmasi logout
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()

                    //pindah ke halaman login
                    val intent = Intent(this, ThirdActivity::class.java)
                    startActivity(intent)
                    finish() // Activity lama dihapus dari stack

                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog","logout dibatalkan!!")

                    //muncul snackbar logout dibatalkan
                    Snackbar.make(binding.root, "Logout Dibatalkan!", Snackbar.LENGTH_SHORT)
                        .setAction("Tutup"){
                            Log.e("Info Snackbar","Snackbar ditutup")
                        }
                        .show()

                }
                .show()
        }
    }
}