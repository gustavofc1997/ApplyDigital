package com.gustavofc97.applydigital.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gustavofc97.applydigital.data.entity.ArticleEntity

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrUpdateArticle(message: List<ArticleEntity>)

    @Query("SELECT * FROM Article Where visible=:show")
    fun getArticles(show: Boolean = true): List<ArticleEntity>

    @Query("UPDATE Article SET visible=:visible WHERE id = :articleId")
    fun changeArticleVisibility(visible: Boolean = false, articleId: Int)
}