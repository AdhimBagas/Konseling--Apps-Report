package com.adhimbagas.finalprojectskripsi.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.adhimbagas.finalprojectskripsi.BuildConfig
import com.adhimbagas.finalprojectskripsi.databinding.ActivityDetailKonselorHomeBinding
import com.adhimbagas.finalprojectskripsi.model.KonselorModel
import com.bumptech.glide.Glide

class DetailKonselorHome : AppCompatActivity() {

    private lateinit var binding: ActivityDetailKonselorHomeBinding


    companion object {
        const val DETAIL_KONSELOR = "DETAIL_KONSELOR"
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailKonselorHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val toolBar = binding.toolbar2
        setSupportActionBar(toolBar)
        if (BuildConfig.DEBUG && supportActionBar == null) {
            error("Assertion failed")
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //Set Variable Data String
        val positionTitle = binding.tvKonselorDetailTitle
        val nameKonselor = binding.tvNameKonselorDetail
        val institution = binding.tvInstitution
        val about = binding.tvAbout
        val address = binding.tvAddressKonselorDetail
        val practice = binding.tvDailyPracticeKonselorDetail

        //set Variable Data Image
        val imgKonselor = binding.imgKonselorDetail


        //set button
        val btnWhatsapp = binding.btnWhatsapp


        //Initiation Parcelable EXTRA
        val detailKons = intent.getParcelableExtra<KonselorModel>(DETAIL_KONSELOR) as KonselorModel


        //String Text.
        positionTitle.text = detailKons.position
        nameKonselor.text = detailKons.name
        institution.text = detailKons.institution
        about.text = detailKons.about
        address.text = detailKons.address
        practice.text = detailKons.dailyPractice


        val number = detailKons.whatsapp


        //Glide Image
        Glide.with(this)
            .load(detailKons.image)
            .override(150,150)
            .into(imgKonselor)


        //cardvIew

        btnWhatsapp.setOnClickListener {
            val packageManager: PackageManager = packageManager
            val url = "https://api.whatsapp.com/send?phone=$number"
            val i = Intent(Intent.ACTION_VIEW)
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)

            if (i.resolveActivity(packageManager) != null)
                startActivity(i)
            else
                Toast.makeText(this,"Error Application", Toast.LENGTH_SHORT).show()
        }




    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}



