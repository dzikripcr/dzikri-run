package com.example.dzikri_run.Notification

import android.R.id.message
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.dzikri_run.databinding.ItemNotifikasiBinding
import com.google.android.material.snackbar.Snackbar

class NotifAdapter(
    context: Context,
    private val  Notifikasi: List<NotifModel>
) : ArrayAdapter<NotifModel>(context, 0, Notifikasi) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemNotifikasiBinding = ItemNotifikasiBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root

        val data = Notifikasi[position]

        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        binding.textSender.text = data.senderName
        binding.textNotif.text = data.notifText

        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Pesan dari ${data.senderName}: ${data.notifText}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}