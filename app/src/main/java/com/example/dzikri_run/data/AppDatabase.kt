package com.example.dzikri_run.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dzikri_run.data.dao.ProyekDao
import com.example.dzikri_run.data.dao.WargaDao
import com.example.dzikri_run.data.entity.ProyekEntity
import com.example.dzikri_run.data.entity.WargaEntity

@Database(
    entities = [ProyekEntity::class, WargaEntity::class], // Menambahkan ProyekEntity di sini
    version = 2, // Naikkan versi jika database sudah pernah terbentuk sebelumnya
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun proyekDao(): ProyekDao
    //Tambahkan WargaDao
    abstract fun wargaDao(): WargaDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Tambahkan baris ini, jika sudah selesai maka hapus
                    .build().also { INSTANCE = it }
            }
        }
    }
}