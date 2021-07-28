package com.adhimbagas.finalprojectskripsi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var rvKonselor: RecyclerView
    private var list: ArrayList<Konselor> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvKonselor.findViewById<RecyclerView>(R.id.rv_home)
       rvKonselor.setHasFixedSize(true)

        list.addAll(KonselorData.listData)
        showRecyclerViewList()
    }

    private fun showRecyclerViewList() {
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rvKonselor.layoutManager = layoutManager
        val listKonselorAdapter = HorizontalKonselorAdapter(list)
        rvKonselor.adapter = listKonselorAdapter
    }
}