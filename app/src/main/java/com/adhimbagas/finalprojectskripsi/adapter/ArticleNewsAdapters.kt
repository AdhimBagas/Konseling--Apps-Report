package com.adhimbagas.finalprojectskripsi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhimbagas.finalprojectskripsi.databinding.ItemListArticleBinding
import com.adhimbagas.finalprojectskripsi.model.Article
import com.adhimbagas.finalprojectskripsi.ui.activity.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ArticleNewsAdapters(private val context: Context): RecyclerView.Adapter<ArticleNewsAdapters.ListViewHolder>() {


private val listArticle = ArrayList<Article>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
      holder.bind(listArticle[position])
      val a = listArticle[position]
        holder.binding.cardHost.setOnClickListener {
//            Toast.makeText(context, , Toast.LENGTH_SHORT).show()

            val dataArticle = Article(
                    a.title,
                    a.imageArticle,
                    a.dateCreated,
                    a.content,
                    a.link
            )

            val moveIntent = Intent(context, DetailActivity::class.java)
            moveIntent.putExtra(DetailActivity.DETAIL_NEWS, dataArticle)
            context.startActivity(moveIntent)
        }
    }

    override fun getItemCount(): Int = listArticle.size

    fun updateArticle(updateArticle: ArrayList<Article>){
        listArticle.clear()
        listArticle.addAll(updateArticle)

        notifyDataSetChanged()
    }
    class ListViewHolder(val binding: ItemListArticleBinding): RecyclerView.ViewHolder(binding.root) {

        private val image = binding.imgrticle
        private val title = binding.tvId
        private val dateCreated = binding.tvDate
        fun bind(article: Article){
            Glide.with(itemView.context)
                    .load(article.imageArticle)
                    .apply(RequestOptions().override(80, 80))
                    .into(image)

            title.text = article.title
            dateCreated.text = article.dateCreated
        }

    }
}