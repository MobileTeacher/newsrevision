package io.github.mobileteacher.newsrevision

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.mobileteacher.newsrevision.api.RetrofitProvider
import io.github.mobileteacher.newsrevision.models.News
import io.github.mobileteacher.newsrevision.models.NewsResponseObject
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {

    private var _errorMessage:String = ""
    set(value) {
        field = value
        errorMessage.value = value
    }
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    val newsList = MutableLiveData<List<News>>()
    val isLoading = MutableLiveData<Boolean>()



    fun getData(){
        val call = RetrofitProvider.newsAPI.getAllNews()
        isLoading.value = true
        call.enqueue(object : Callback<NewsResponseObject> {
            override fun onFailure(call: Call<NewsResponseObject>, t: Throwable) {
                _errorMessage = "Deu ruim: ${t.message}"
                isLoading.value = false
            }

            override fun onResponse(call: Call<NewsResponseObject>,
                                    response: Response<NewsResponseObject>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {newsResponseObject->
                        newsList.value = newsResponseObject.news
                    }
                } else {
                    _errorMessage = "Deu ruim, fale com o admin"
                }
                isLoading.value = false
            }

        })
    }
}