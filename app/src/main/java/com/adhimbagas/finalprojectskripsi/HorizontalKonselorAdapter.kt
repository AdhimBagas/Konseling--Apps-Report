package com.adhimbagas.finalprojectskripsi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HorizontalKonselorAdapter (private val listKonselor: ArrayList<Konselor>): RecyclerView.Adapter<HorizontalKonselorAdapter.ListViewHolder>() {


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tvNameKonselor : TextView = itemView.findViewById(R.id.tv_konselor)
            var tvJabatan: TextView = itemView.findViewById(R.id.tv_jabatan)
            var imgPhoto: ImageView = itemView.findViewById(R.id.img_konselor)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
       val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_cardviewnested_home, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val konselor = listKonselor [position]

        Glide.with(holder.itemView.context)
                .load(konselor.photo)
                .into(holder.imgPhoto)

        holder.tvNameKonselor.text = konselor.name
        holder.tvJabatan.text = konselor.jabatan
    }

    override fun getItemCount(): Int {
        return listKonselor.size
    }


}