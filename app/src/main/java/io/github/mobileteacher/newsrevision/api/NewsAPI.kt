package io.github.mobileteacher.newsrevision.api

import io.github.mobileteacher.newsrevision.models.NewsResponseObject
import retrofit2.Call
import retrofit2.http.GET

interface NewsAPI {

    @GET("noticias")
    fun getAllNews(): Call<NewsResponseObject>

    //delete passa id da noticia
}