package com.example.dzikri_run.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proyek")
data class ProyekEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaProyek: String,
    val anggaranProyek: String,
    val gambarProyek: String
)