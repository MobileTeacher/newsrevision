package io.github.mobileteacher.newsrevision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.mobileteacher.newsrevision.models.News
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val news = intent.getParcelableExtra<News>(NEWS_EXTRA)

        details_textview.text = "${news.titulo}\n ${news.autor}\n${news.texto}"
    }
}
