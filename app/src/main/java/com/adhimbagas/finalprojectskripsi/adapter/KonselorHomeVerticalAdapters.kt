package com.adhimbagas.finalprojectskripsi.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhimbagas.finalprojectskripsi.databinding.ItemListVerticalKonselorHomeBinding
import com.adhimbagas.finalprojectskripsi.model.KonselorModel
import com.bumptech.glide.Glide

class KonselorHomeVerticalAdapters (private val context: Context) : RecyclerView.Adapter<KonselorHomeVerticalAdapters.ListViewHolder>() {

    private val listKonselor = ArrayList<KonselorModel>()

    class ListViewHolder (val binding: ItemListVerticalKonselorHomeBinding): RecyclerView.ViewHolder(binding.root){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListVerticalKonselorHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listKonselor[position])

        val current = listKonselor[position]
        val number = current.whatsapp

        holder.binding.imageButton.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=$number"
            val i = Intent(Intent.ACTION_VIEW)
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
       return listKonselor.size
    }

    fun setData(konselorItems: List<KonselorModel>) {
        listKonselor.clear()
        listKonselor.addAll(konselorItems)
    }


}