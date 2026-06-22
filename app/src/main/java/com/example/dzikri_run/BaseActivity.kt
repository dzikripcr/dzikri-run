package com.example.dzikri_run

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.dzikri_run.About.AboutFragment
import com.example.dzikri_run.Home.HomeFragment
import com.example.dzikri_run.Notification.NotificationFragment
import com.example.dzikri_run.Profile.ProfileFragment
import com.example.dzikri_run.databinding.ActivityBaseBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        Log.e("onCreate", "BaseActivity dibuat pertama kali")
        /** FragmentHome sebagai fragment default */
        replaceFragment(HomeFragment())

        //Kode ini harus selalu dipanggil saat butuh akses "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.about -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                R.id.logout -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Logout")
                        .setMessage("Apakah Anda yakin ingin melanjutkan?")
                        .setPositiveButton("Ya") { dialog, _ ->
                            sharedPref.edit {
                                clear()
                            }
                            val intent = Intent(this, AuthActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .setNegativeButton("Batal") { dialog, _ ->
                            dialog.dismiss()
                            Snackbar.make(binding.root, "Logout Berhasil di batalkan", Snackbar.LENGTH_SHORT)
                                .setAction("Tutup"){
                                    Log.e("Info Snackbar","Snackbar ditutup")
                                }
                                .show()
                        }
                        .show()
                    true
                }
                else ->false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            //.addToBackStack(null) -> ini kita nonaktifkan agar saat back langsung keluar aplikasi
            .commit()
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: BaseActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "BaseActivity dihapus dari stack")
    }
}