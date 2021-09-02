package com.adhimbagas.finalprojectskripsi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhimbagas.finalprojectskripsi.R
import com.adhimbagas.finalprojectskripsi.databinding.ItemListArticleBinding
import com.adhimbagas.finalprojectskripsi.model.Article
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ArticleNewsAdapters: RecyclerView.Adapter<ArticleNewsAdapters.ListViewHolder>() {


    private val listArticle = ArrayList<Article>()
    fun setData (items: ArrayList<Article>){
        listArticle.clear()
        listArticle.addAll(items)
        notifyDataSetChanged()
    }

    class ListViewHolder(binding: ItemListArticleBinding): RecyclerView.ViewHolder(binding.root) {

        val image = binding.imgrticle
        val title = binding.tvId
        val dateCreated = binding.tvDate

    fun bind (article: Article){
        with(itemView){
            Glide.with(itemView.context)
                .load(article.imageArticle)
                .apply(RequestOptions().override(80,80))
                .into(image)

            title.text = article.title
            dateCreated.text = article.dateCreated
            //                tv_type.text = user.type
        }
    }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListArticleBinding.inflate(LayoutInflater.from(parent.context),parent, false)

            //LayoutInflater.from(parent.context).inflate(R.layout.item_list_article, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listArticle[position])
    }

    override fun getItemCount(): Int = listArticle.size
}