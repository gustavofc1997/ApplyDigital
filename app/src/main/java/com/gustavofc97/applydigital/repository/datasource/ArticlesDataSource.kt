package com.gustavofc97.applydigital.repository.datasource

import androidx.room.withTransaction
import com.gustavofc97.applydigital.data.AppDatabase
import com.gustavofc97.applydigital.data.entity.ArticleEntity
import com.gustavofc97.applydigital.data.network.API
import javax.inject.Inject

class ArticlesDataSource @Inject constructor(
    private val appDatabase: AppDatabase, private val api: API
) {

    private val articlesDao = appDatabase.articlesDao()
    suspend fun loadArticles(): List<ArticleEntity> {
        val articles = api.getArticles()
        appDatabase.withTransaction {
            articlesDao.insertOrUpdateArticle(articles.hits.map { it.toEntity() })
        }
        return articlesDao.getArticles()
    }

    suspend fun deleteArticle(articleId: Int) {
        appDatabase.withTransaction {
            articlesDao.changeArticleVisibility(articleId = articleId)
        }
    }
}