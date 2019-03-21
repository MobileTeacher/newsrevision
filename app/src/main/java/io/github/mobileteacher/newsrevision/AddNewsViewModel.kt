package io.github.mobileteacher.newsrevision

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddNewsViewModel: ViewModel() {
    val date = MutableLiveData<String>()
    val pageNumber = MutableLiveData<Int>().apply { value = 0 }


    fun incPage(){
        pageNumber.value = pageNumber.value?.plus(1)
    }

    fun decPage(){
        pageNumber.value = pageNumber.value?.minus(1)
    }
}