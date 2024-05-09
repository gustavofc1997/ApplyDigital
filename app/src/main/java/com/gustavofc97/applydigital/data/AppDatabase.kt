package com.gustavofc97.applydigital.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gustavofc97.applydigital.data.dao.ArticleDao
import com.gustavofc97.applydigital.data.entity.ArticleEntity

@Database(
    version = 1,
    entities = [ArticleEntity::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticleDao
}
