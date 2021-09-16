package com.adhimbagas.finalprojectskripsi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhimbagas.finalprojectskripsi.databinding.ItemListKonselorHomeBinding
import com.adhimbagas.finalprojectskripsi.model.KonselorModel
import com.bumptech.glide.Glide

class KonselorHomeAdapters (private val context: Context): RecyclerView.Adapter<KonselorHomeAdapters.ListViewHolder>() {

    private val listKonselor = ArrayList<KonselorModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListKonselorHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listKonselor[position])
    }

    override fun getItemCount(): Int {
        return listKonselor.size
    }

    fun setData(konselorItems: List<KonselorModel>) {
        listKonselor.clear()
        listKonselor.addAll(konselorItems)
    }


   class ListViewHolder (val binding: ItemListKonselorHomeBinding): RecyclerView.ViewHolder(binding.root){
        private val nameKonselor = binding.tvKonselor
        private val institutionKonselor = binding.tvInstitution
        private val imageKonselor = binding.imgKonselorHome

        fun bind (konselor: KonselorModel){
            nameKonselor.text = konselor.name
            institutionKonselor.text = konselor.institution
            Glide.with(itemView.context)
                    .load(konselor.image)
                    .into(imageKonselor)
        }

   }

}