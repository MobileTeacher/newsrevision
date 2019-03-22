package io.github.mobileteacher.newsrevision

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.mobileteacher.newsrevision.models.News

@Dao
interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(newsList: List<News>)

    @Query("SELECT * FROM News")
    fun getAllNews(): LiveData<List<News>>

}