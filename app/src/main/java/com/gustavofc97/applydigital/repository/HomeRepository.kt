package com.gustavofc97.applydigital.repository

import com.gustavofc97.applydigital.data.model.ArticleResource

interface HomeRepository {
     suspend fun loadData(): List<ArticleResource>
     suspend fun deleteArticle(articleResourceId: Int)
}
