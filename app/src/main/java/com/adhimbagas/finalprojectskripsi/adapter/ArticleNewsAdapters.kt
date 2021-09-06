package com.adhimbagas.finalprojectskripsi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhimbagas.finalprojectskripsi.databinding.ItemListArticleBinding
import com.adhimbagas.finalprojectskripsi.model.Article
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ArticleNewsAdapters(): RecyclerView.Adapter<ArticleNewsAdapters.ListViewHolder>() {


private val listArticle = ArrayList<Article>()
//    fun setData (items: ArrayList<Article>){
//        listArticle.clear()
//        listArticle.addAll(items)
//        notifyDataSetChanged()
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListArticleBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
      holder.bind(listArticle[position])
    }

    override fun getItemCount(): Int = listArticle.size

    fun updateArticle (updateArticle: ArrayList<Article>){
        listArticle.clear()
        listArticle.addAll(updateArticle)

        notifyDataSetChanged()
    }
    class ListViewHolder(val binding: ItemListArticleBinding): RecyclerView.ViewHolder(binding.root) {

        private val image = binding.imgrticle
        private val title = binding.tvId
        private val dateCreated = binding.tvDate

        fun bind (article: Article){
            with(itemView){
                Glide.with(itemView.context)
                        .load(article.imageArticle)
                        .apply(RequestOptions().override(80,80))
                        .into(image)

                title.text = article.title
                dateCreated.text = article.dateCreated

            }
        }

    }

//    interface ArticleClicked {
//
//    }
}