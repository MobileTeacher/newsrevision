package io.github.mobileteacher.newsrevision

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.mobileteacher.newsrevision.adapters.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsViewModel = ViewModelProviders.of(this)
                            .get(NewsViewModel::class.java)

        setupRecyclerView()
        subscribe()
        setupListeners()
    }

    private fun setupListeners(){
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddNewsActivity::class.java))
        }
    }

    private fun setupRecyclerView(){
        val columns = if(resources.configuration.orientation
                        == Configuration.ORIENTATION_PORTRAIT) 2
        else 3

        news_list.layoutManager = StaggeredGridLayoutManager(columns,
            StaggeredGridLayoutManager.VERTICAL)//LinearLayoutManager(this)

        news_list.adapter = NewsAdapter{news->
            startActivity(Intent(this,
                NewsDetailActivity::class.java).apply {
                putExtra(NEWS_EXTRA, news)
            })
        }
        newsViewModel.getData()
    }

    private fun subscribe(){
        newsViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        newsViewModel.newsList.observe(this, Observer {list->
            val adapter = news_list.adapter as? NewsAdapter
            adapter?.setData(list)
        })

        newsViewModel.isLoading.observe(this, Observer {
            if (it){
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }


}
