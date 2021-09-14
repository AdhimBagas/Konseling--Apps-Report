package com.adhimbagas.finalprojectskripsi.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.adhimbagas.finalprojectskripsi.BuildConfig
import com.adhimbagas.finalprojectskripsi.databinding.ActivityDetailBinding
import com.adhimbagas.finalprojectskripsi.model.Article
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailBinding
    private val TAG = "webView_TAG"


    companion object {
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolBar = binding.toolbar
        setSupportActionBar(toolBar)
        if (BuildConfig.DEBUG && supportActionBar == null) {
            error("Assertion failed")
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //Set Article
        val title = binding.titleDetail
        val date = binding.dateDetail
        val imgDetail = binding.imgDetail
        val titleBar = binding.titleBar
        val btnShare = binding.imgShare

        val webView = binding.webDetail



        val articleVal = intent.getParcelableExtra<Article>(DETAIL_NEWS) as Article
        title.text = articleVal.title
        titleBar.text = articleVal.title
        date.text =  articleVal.dateCreated


        Glide.with(this)
                .load(articleVal.imageArticle)
                .into(imgDetail)

        val strNewsUrl = articleVal.link
        val contentDetail = articleVal.content


        btnShare.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            share.putExtra(Intent.EXTRA_TEXT, strNewsUrl)
            startActivity(Intent.createChooser(share, "Bagikan ke : "))
        }

        contentDetail?.let { webView.loadDataWithBaseURL("", it,"text/html","UTF-8","") }
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Log.d(TAG, "back")
        return super.onSupportNavigateUp()
    }

}
