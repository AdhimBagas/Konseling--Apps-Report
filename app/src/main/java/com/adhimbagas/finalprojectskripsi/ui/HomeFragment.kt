package com.adhimbagas.finalprojectskripsi.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adhimbagas.finalprojectskripsi.adapter.KonselorHomeAdapters
import com.adhimbagas.finalprojectskripsi.adapter.KonselorHomeVerticalAdapters
import com.adhimbagas.finalprojectskripsi.databinding.FragmentHomeBinding
import com.adhimbagas.finalprojectskripsi.model.KonselorData
import com.adhimbagas.finalprojectskripsi.model.KonselorModel

class HomeFragment : Fragment(), LifecycleObserver {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var rvArticle: RecyclerView
    private lateinit var rvKons: RecyclerView
    private lateinit var mAdapters: KonselorHomeAdapters
    private lateinit var mAdaptersVetical: KonselorHomeVerticalAdapters

    private fun getKonselor(): List<KonselorModel>{
        return KonselorData.generateKons()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onCreated(){

        rvArticle = binding.recyclerView2

        rvKons = binding.rvListKonselorHome

        mAdapters = KonselorHomeAdapters(requireContext())
        mAdapters.setData(getKonselor())

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rvArticle.setHasFixedSize(true)
        rvArticle.layoutManager = linearLayoutManager
        rvArticle.adapter = mAdapters


        setUpKonsVertical()
    }

    private fun setUpKonsVertical() {


        mAdaptersVetical = KonselorHomeVerticalAdapters(requireContext())
        mAdaptersVetical.setData(getKonselor())

        rvKons.layoutManager  = LinearLayoutManager(requireContext())
        rvKons.adapter = mAdaptersVetical


    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(this)
//        onCreated()
    }

    override fun onDetach() {
        super.onDetach()
        lifecycle.removeObserver(this)
    }

}