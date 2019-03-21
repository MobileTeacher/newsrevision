package io.github.mobileteacher.newsrevision.api

import io.github.mobileteacher.newsrevision.models.News
import io.github.mobileteacher.newsrevision.models.NewsResponseObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NewsAPI {

    @GET("noticias")
    fun getAllNews(): Call<NewsResponseObject>


    @POST("noticias")
    fun createNews(@Body news: News): Call<News>
    //delete passa id da noticia
}