package com.example.dzikri_run.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.dzikri_run.BaseActivity
import com.example.dzikri_run.Home.pertemuan10.TenthActivity

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        /**
        Aksi yang dilakukan saat reminder berhasil dipanggil.
        Dalam contoh ini ialah menampilkan NotificationHelper
         **/

        val title           = intent.getStringExtra("title") ?: "Pengingat"
        val message         = intent.getStringExtra("message") ?: "Waktunya melakukan sesuatu"
        val targetClassName = intent.getStringExtra("target_activity")

        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            val clazz = Class.forName(targetClassName)
            Intent(context, clazz).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                if (clazz == TenthActivity::class.java) {
                    putExtra(TenthActivity.EXTRA_OPEN_TAB, TenthActivity.TAB_NOTIFIKASI)
                }
            }
        } else {
            Intent(context, BaseActivity::class.java)
        }

        NotificationHelper.showNotification(
            context = context,
            title = title,
            message = message,
            intent = targetIntent
        )
    }
}