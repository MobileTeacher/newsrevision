package io.github.mobileteacher.newsrevision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.mobileteacher.newsrevision.adapters.AddNewsFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_add_news.*

class AddNewsActivity : AppCompatActivity() {

    private lateinit var addNewsViewModel: AddNewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)

        addNewsViewModel = ViewModelProviders.of(this)
            .get(AddNewsViewModel::class.java)

        view_pager.adapter = AddNewsFragmentPagerAdapter(supportFragmentManager)

        setSupportActionBar(add_news_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        subscribe()
    }

    private fun subscribe(){
        addNewsViewModel.pageNumber.observe(this, Observer {
            view_pager.currentItem = it
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        addNewsViewModel.pageNumber.value?.let {pageNumber->
            if (pageNumber > 0){
                addNewsViewModel.decPage()
                return
            }
        }
        super.onBackPressed()
    }


}

