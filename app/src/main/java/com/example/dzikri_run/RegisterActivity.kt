package com.example.dzikri_run

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.SharedPreferences
import com.example.dzikri_run.databinding.ActivityRegisterBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences("USER_DATA", MODE_PRIVATE)

        val email = intent.getStringExtra("EMAIL")
        binding.etEmail.setText(email)

        binding.btnRegister.setOnClickListener {

            val nama = binding.etNama.text.toString().trim()
            val emailUser = binding.etEmail.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            when {

                nama.isEmpty() ||
                        emailUser.isEmpty() ||
                        username.isEmpty() ||
                        password.isEmpty() -> {

                    showError("Semua field wajib diisi")
                }

                password.length < 6 -> {

                    showError("Password minimal 6 karakter")
                }

                username.contains(" ") -> {

                    showError("Username tidak boleh mengandung spasi")
                }

                else -> {

                    saveData(nama, emailUser, username, password)

                    val intent = Intent(this, SuccessActivity::class.java)
                    startActivity(intent)

                    finish()

                }
            }
        }
    }

    private fun saveData(
        nama: String,
        email: String,
        username: String,
        password: String
    ) {

        val editor = sharedPreferences.edit()

        editor.putString("NAMA", nama)
        editor.putString("EMAIL", email)
        editor.putString("USERNAME", username)
        editor.putString("PASSWORD", password)

        editor.apply()
    }

    private fun showError(message: String) {

        MaterialAlertDialogBuilder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()

    }
}