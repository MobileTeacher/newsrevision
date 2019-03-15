package io.github.mobileteacher.newsrevision

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddNewsViewModel: ViewModel() {
    val date = MutableLiveData<String>()
}