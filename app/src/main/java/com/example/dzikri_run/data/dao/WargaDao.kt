package com.example.dzikri_run.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dzikri_run.data.entity.WargaEntity

@Dao
interface WargaDao {
    // Mengambil semua data warga
    @Query("SELECT * FROM warga")
    suspend fun getAllWarga(): List<WargaEntity>

    // Menambahkan data warga baru
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(warga: WargaEntity): Long

    // Menghapus data warga (Tipe Int ditambahkan untuk menghindari bug KSP)
    @Delete
    suspend fun delete(warga: WargaEntity): Int
}