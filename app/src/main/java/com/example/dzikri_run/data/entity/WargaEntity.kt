package com.example.dzikri_run.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "warga")
data class WargaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nik: String,
    val nama: String,
    val jenisKelamin: String,
    val noHp: String
)