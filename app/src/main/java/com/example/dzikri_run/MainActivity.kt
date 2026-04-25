package com.example.dzikri_run

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzikri_run.databinding.ActivityMainBinding
import com.example.dzikri_run.pertemuan3.ThirdActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Home"
            subtitle = "Silahkan berbelanja!"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //Kode ini harus selalu dipanggil saat butuh akses "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        return when (item.itemId) {

            R.id.action_logout -> {
                Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show()
                MaterialAlertDialogBuilder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Apakah Anda yakin ingin melanjutkan?")
                    .setPositiveButton("Ya") { dialog, _ ->
                        sharedPref.edit {
                            clear()
                        }
                        val intent = Intent(this, ThirdActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
                true
            }

            R.id.action_profile -> {
                Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_cart -> {
                Toast.makeText(this, "Cart Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_favorite -> {
                Toast.makeText(this, "Favorite Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}