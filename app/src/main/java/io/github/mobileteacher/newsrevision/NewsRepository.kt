package io.github.mobileteacher.newsrevision

import android.content.Context
import androidx.lifecycle.LiveData
import io.github.mobileteacher.newsrevision.api.NewsAPI
import io.github.mobileteacher.newsrevision.api.RetrofitProvider
import io.github.mobileteacher.newsrevision.models.News
import io.github.mobileteacher.newsrevision.models.NewsResponseObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(context: Context) {

    val newsDAO: NewsDAO = NewsDatabase.get(context).newsDao()
    val newsApi: NewsAPI = RetrofitProvider.newsAPI

    fun insertNews(news: News, succesAction: ()->Unit, failureAction: ()->Unit){
        val call = RetrofitProvider.newsAPI.createNews(news)
        call.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
//                errorMessage.value = t.message
//                isLoading.value = false
                failureAction()
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful){
//                    errorMessage.value = "Notícia adicionada com sucesso"
//                    isLoading.value = false
//                    shouldFinish.value = true
                    response.body()?.let {createdNews->
                        newsDAO.insert(createdNews)
                    } ?: run {
                        // se for nulo
                    }
                    succesAction()
                }
            }

        })
    }

    fun allNews():LiveData<List<News>>{
        val newsList = newsDAO.getAllNews()
        // verificar critério de validade do cache
        val shouldCache = newsList.value?.isEmpty() ?: true
        if (shouldCache){
            cacheData()
        }
        return newsList
    }

    fun cacheData(){
        val call = newsApi.getAllNews()
        call.enqueue(object : Callback<NewsResponseObject>{
            override fun onFailure(call: Call<NewsResponseObject>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<NewsResponseObject>, response: Response<NewsResponseObject>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        newsDAO.insertAll(it.news)
                    }
                }
            }

        })
    }

}