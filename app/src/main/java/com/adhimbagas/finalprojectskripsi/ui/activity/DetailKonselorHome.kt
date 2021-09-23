package com.adhimbagas.finalprojectskripsi.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.adhimbagas.finalprojectskripsi.BuildConfig
import com.adhimbagas.finalprojectskripsi.databinding.ActivityDetailKonselorHomeBinding
import com.adhimbagas.finalprojectskripsi.model.KonselorModel
import com.bumptech.glide.Glide

class DetailKonselorHome : AppCompatActivity() {

    private lateinit var binding: ActivityDetailKonselorHomeBinding
    private val TAG = "konselor_home"

    companion object {
        const val DETAIL_KONSELOR = "DETAIL_KONSELOR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailKonselorHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val toolBar = binding.toolbarBack
        setSupportActionBar(toolBar)
        if (BuildConfig.DEBUG && supportActionBar == null) {
            error("Assertion failed")
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

            //Set Variable Data String
            val positionTitle = binding.tvKonselorDetailTitle
//            val nameKonselor = binding.tvNameKonselorDetail
//            val institution = binding.tvInstitutionKonselorDetail
//            val about = binding.tvAboutKonselorDetail
//            val address = binding.tvAddressKonselorDetail
//            val practice = binding.tvDailyPracticeKonselorDetail

            //set Variable Data Image
            val imgKonselor = binding.imgKonselorDetail

            //Set variable Data CardView
//            val cvEmail = binding.cvEmail
//            val cvWhatsapp = binding.cvWhatsapp
//            val cvInstagram = binding.cvInstagram

            //set button
//            val btnWhatsapp = binding.btnWhatsapp


            //Initiation Parcelable EXTRA
            val detailKons = intent.getParcelableExtra<KonselorModel>(DETAIL_KONSELOR) as KonselorModel


            //String Text.
            positionTitle.text = detailKons.position
//            nameKonselor.text = detailKons.name
//            institution.text = detailKons.institution
//            about.text = detailKons.about
//            address.text = detailKons.address
//            practice.text = detailKons.dailyPractice


            //Glide Image
            Glide.with(this).
                    load(detailKons.image)
                    .override(150,150)
                    .into(imgKonselor)
                     }

            //cardvIew


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Log.d(TAG,"Back")
        return super.onSupportNavigateUp()
    }
}



