package com.example.dzikri_run

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzikri_run.databinding.ActivityInputGmailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class InputGmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputGmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInputGmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnNext.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()

            when {

                email.isEmpty() -> {
                    showError("Email tidak boleh kosong")
                }

                !email.endsWith("@gmail.com") -> {
                    showError("Email harus menggunakan domain @gmail.com")
                }

                else -> {

                    val intent = Intent(this, RegisterActivity::class.java)
                    intent.putExtra("EMAIL", email)
                    startActivity(intent)

                }
            }
        }

    }

    private fun showError(message: String) {

        MaterialAlertDialogBuilder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()

    }
}