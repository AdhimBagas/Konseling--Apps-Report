package com.adhimbagas.finalprojectskripsi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhimbagas.finalprojectskripsi.databinding.ItemListVerticalKonselorHomeBinding
import com.adhimbagas.finalprojectskripsi.model.KonselorModel
import com.bumptech.glide.Glide

class KonselorHomeVerticalAdapters (private val context: Context) : RecyclerView.Adapter<KonselorHomeVerticalAdapters.ListViewHolder>() {

    private val listKonselor = ArrayList<KonselorModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListVerticalKonselorHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class ListViewHolder (binding: ItemListVerticalKonselorHomeBinding): RecyclerView.ViewHolder(binding.root){
            private val nameKons = binding.tvName
            private val instKons = binding.tvIns
            private val imgKons = binding.imgKonsVertical

        fun bind (konselor: KonselorModel){

            nameKons.text = konselor.name
            instKons.text = konselor.institution
            Glide.with(itemView.context)
                    .load(konselor.image)
                    .into(imgKons)

        }
    }
}