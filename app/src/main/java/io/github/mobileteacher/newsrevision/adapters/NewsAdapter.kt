package io.github.mobileteacher.newsrevision.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.mobileteacher.newsrevision.R
import io.github.mobileteacher.newsrevision.models.News

class NewsAdapter(val onItemClick: ((news: News)->Unit)? = null):
            RecyclerView.Adapter<RecyclerView.ViewHolder>() {




    var items = listOf<News>(News("dsds",
        "Ninja", "Homem pisa em Marte",
        "Sem texto",
        "feito histórico aconteceu no último dia 15",
        "28/06/2030"))
    //val items2: MutableList<News> = mutableListOf()

    override fun getItemCount() = items.size

    fun setData(list: List<News>){
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val card = LayoutInflater.from(parent.context)
                        .inflate(R.layout.card_news_item, parent,false)
        return NewsViewHolder(card)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = items[position]
        //val newsViewHolder = holder as? NewsViewHolder
        if (holder is NewsViewHolder){
            holder.titleTextView.text = news.titulo
            holder.informativeTextView.text = news.informativo
            holder.authorTextView.text = news.autor
            holder.dateTextview.text = news.data
        }
    }


    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val titleTextView = itemView.findViewById<TextView>(R.id.titulo_textview)
        val informativeTextView = itemView.findViewById<TextView>(R.id.informativo_textview)
        val authorTextView = itemView.findViewById<TextView>(R.id.autor_textview)
        val dateTextview = itemView.findViewById<TextView>(R.id.data_textview)

        init {
            itemView.setOnClickListener {
                val news = items[adapterPosition]
                onItemClick?.invoke(news)
            }
        }
    }
}