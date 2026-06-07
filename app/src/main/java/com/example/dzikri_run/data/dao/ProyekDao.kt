package com.example.dzikri_run.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dzikri_run.data.entity.ProyekEntity

@Dao
interface ProyekDao {
    // Mengambil semua data proyek
    @Query("SELECT * FROM proyek")
    suspend fun getAllProyek(): List<ProyekEntity>

    // Menambahkan satu proyek
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(proyek: ProyekEntity): Long

    // Menambahkan banyak proyek sekaligus (berguna untuk inisialisasi list awal)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(proyekList: List<ProyekEntity>): List<Long>

    @Delete
    suspend fun delete(proyek: ProyekEntity): Int
}