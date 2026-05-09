package com.example.dzikri_run

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzikri_run.databinding.ActivityAuthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Kode ini harus selalu dipanggil saat butuh akses "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

//        //Kondisi jika isLogin bernilai true
//        val isLogin = sharedPref.getBoolean("isLogin", false)
//        if (isLogin) {
//            //Panggil Intent untuk ke MainActivity
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username == password && username.isNotEmpty() && password.isNotEmpty()){
                sharedPref.edit {
                    putBoolean("isLogin", true)
                    putString("username", username)
                }

                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                MaterialAlertDialogBuilder(this)
                    .setTitle("Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("Close") { dialog, _ ->
                        dialog.dismiss()
                        Log.e("Info Dialog","Anda mengklik close pop up!")
                    }
                    .show()
            }
        }
    }
}