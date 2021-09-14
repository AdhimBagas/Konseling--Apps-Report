package com.adhimbagas.finalprojectskripsi.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adhimbagas.finalprojectskripsi.adapter.ArticleNewsAdapters
import com.adhimbagas.finalprojectskripsi.databinding.FragmentArticleBinding
import com.adhimbagas.finalprojectskripsi.model.Article
import com.android.volley.Request
import com.android.volley.toolbox.*
import org.json.JSONException
import org.json.JSONObject

class ArticleFragment : Fragment(), LifecycleObserver {

    private lateinit var binding: FragmentArticleBinding
  private lateinit var rvArticle: RecyclerView

    private lateinit var mAdapters: ArticleNewsAdapters
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentArticleBinding.inflate(layoutInflater)
        return binding.root
     }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onCreated(){
        Log.i("tag","RecyclerView Created")

        rvArticle  = binding.rvArticle

//        Cobaa
        rvArticle.layoutManager  = LinearLayoutManager(requireContext())
        setArticleData()
        mAdapters = ArticleNewsAdapters(requireContext())
        rvArticle.adapter = mAdapters
//        articleAdapter = ArticleNewsAdapters()
//        articleAdapter.notifyDataSetChanged()
////
////        rvArticle.layoutManager = LinearLayoutManager(requireContext())
//        val linearLayoutManager: LinearLayoutManager= LinearLayoutManager(requireContext())
//        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//
//        rvArticle.setHasFixedSize(true)
//        rvArticle.layoutManager = linearLayoutManager
//        rvArticle.adapter = articleAdapter
//
//
//        setArticleData()

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

    private fun setArticleData() {

        val url = "https://forkombi.com/wp-json/wp/v2/posts?per_page=5"

       val queue = Volley.newRequestQueue(this.context)
        // Formulate the request and handle the response.
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url,null,
                { response ->
                    // Do something with the response
                    Log.d(TAG, "onResponse: $response")
                  try {
                      val listArticle = ArrayList<Article>()
                        for (i in 0 until response.length()){
                            //initiatin object Article

                            val article = Article()

                            //initiate jsonObjectdata all
                            val jsonObjectData: JSONObject = response.getJSONObject(i)
                            //initiate data date article
                           article.dateCreated = jsonObjectData.getString("date")

                            //initiate jsonObject Title
                            val jsonObjectTitle: JSONObject = jsonObjectData.getJSONObject("title")
                            article.title = jsonObjectTitle.getString("rendered")

                            //Initiate featured image
                            article.imageArticle = jsonObjectData.getString("featured_media_src_url")

                            //Initiate Content
                            val jsonObjectContent: JSONObject = jsonObjectData.getJSONObject("content")
                            article.content = jsonObjectContent.getString("rendered")
                            listArticle.add(article)

                            //Initiate Link URL
                            article.link = jsonObjectData.getString("link")
                        }
                      mAdapters.updateArticle(listArticle)

                  }
                  catch (e: JSONException){
                      e.message?.let { Log.d(TAG, it) }
                  }

                },
                )
                { error ->
                        error.printStackTrace()
                }

        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest)
    }

}