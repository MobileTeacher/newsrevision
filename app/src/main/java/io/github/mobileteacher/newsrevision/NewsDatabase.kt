package io.github.mobileteacher.newsrevision

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.mobileteacher.newsrevision.models.News

@Database(entities = [News::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDAO

    companion object {
        var INSTANCE: NewsDatabase? = null
        fun get(context: Context): NewsDatabase{
            return INSTANCE ?: run {
                val instance = Room.databaseBuilder(
                    context,
                    NewsDatabase::class.java,
                    "NewsDatabase"
                ).allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

}