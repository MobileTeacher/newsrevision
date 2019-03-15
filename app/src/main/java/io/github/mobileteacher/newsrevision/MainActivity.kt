package io.github.mobileteacher.newsrevision

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.mobileteacher.newsrevision.api.RetrofitProvider
import io.github.mobileteacher.newsrevision.models.NewsResponseObject
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        val columns = if(resources.configuration.orientation
                        == Configuration.ORIENTATION_PORTRAIT) 2
        else 3

        news_list.layoutManager = StaggeredGridLayoutManager(columns,
            StaggeredGridLayoutManager.VERTICAL)//LinearLayoutManager(this)
        news_list.adapter = NewsAdapter()
        getData()
    }

    private fun getData(){
        val call = RetrofitProvider.newsAPI.getAllNews()
        call.enqueue(object : Callback<NewsResponseObject>{
            override fun onFailure(call: Call<NewsResponseObject>, t: Throwable) {
                Toast.makeText(this@MainActivity,
                    "Deu ruim: ${t.message}",
                    Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<NewsResponseObject>,
                                    response: Response<NewsResponseObject>) {
                if (response.isSuccessful){
                    response.body()?.let {newsResponseObject->
                        val adapter = news_list.adapter as? NewsAdapter
                        adapter?.setData(newsResponseObject.news)
                    }
                } else {
                    Toast.makeText(this@MainActivity,
                        "Deu ruim, fale com o admin",
                        Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}
