package io.github.mobileteacher.newsrevision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        news_list.layoutManager = LinearLayoutManager(this)
        //TODO: criar Adapter
        news_list.adapter = NewsAdapter()
    }
}
