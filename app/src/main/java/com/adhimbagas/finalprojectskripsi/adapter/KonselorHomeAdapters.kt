package com.adhimbagas.finalprojectskripsi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhimbagas.finalprojectskripsi.databinding.ItemListKonselorHomeBinding
import com.adhimbagas.finalprojectskripsi.model.KonselorModel
import com.adhimbagas.finalprojectskripsi.ui.activity.DetailKonselorHome
import com.bumptech.glide.Glide

class KonselorHomeAdapters (private val context: Context): RecyclerView.Adapter<KonselorHomeAdapters.ListViewHolder>() {

    private val listKonselor = ArrayList<KonselorModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListKonselorHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listKonselor[position])
        val a = listKonselor[position]

        //Click on listener cardView Konselor Home
        holder.binding.cvKonselorHome.setOnClickListener {
            val dataKonselor  = KonselorModel (
                    a.name,
                    a.institution,
                    a.whatsapp,
                    a.position,
                    a.instagram,
                    a.email,
                    a.about,
                    a.address,
                    a.dailyPractice,
                    a.image
                    )

            val intent = Intent(context, DetailKonselorHome::class.java)
            intent.putExtra(DetailKonselorHome.DETAIL_KONSELOR, dataKonselor)
            context.startActivity(intent)
        }
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