package io.github.mobileteacher.newsrevision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
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

        addNewsViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        addNewsViewModel.isLoading.observe(this, Observer {
            if(it){
                add_news_progressbar.visibility = VISIBLE
            } else {
                add_news_progressbar.visibility = GONE
            }
        })

        addNewsViewModel.shouldFinish.observe(this, Observer {
            if(it){
                finish()
            }
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

