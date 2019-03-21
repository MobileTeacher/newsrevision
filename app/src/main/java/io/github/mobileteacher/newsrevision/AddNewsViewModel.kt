package io.github.mobileteacher.newsrevision

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.mobileteacher.newsrevision.api.RetrofitProvider
import io.github.mobileteacher.newsrevision.models.News
import io.github.mobileteacher.newsrevision.models.NewsResponseObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNewsViewModel: ViewModel() {
    val date = MutableLiveData<String>()
    val pageNumber = MutableLiveData<Int>().apply { value = 0 }

    var newsTitle:String = ""
    var newsInformative = ""
    var newsAuthor = ""
    var newsText = ""

    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val shouldFinish = MutableLiveData<Boolean>()


    fun postNews(){
        val newsDate = date.value ?: ""
        val news = News(newsAuthor,
            newsTitle,
            newsText,
            newsInformative,
            newsDate)

        isLoading.value = true
        val call = RetrofitProvider.newsAPI.createNews(news)
        call.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                errorMessage.value = t.message
                isLoading.value = false
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful){
                    errorMessage.value = "Notícia adicionada com sucesso"
                    isLoading.value = false
                    shouldFinish.value = true
                }
            }

        })
    }

    fun incPage(){
        pageNumber.value = pageNumber.value?.plus(1)
    }

    fun decPage(){
        pageNumber.value = pageNumber.value?.minus(1)
    }
}