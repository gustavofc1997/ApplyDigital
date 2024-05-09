package com.gustavofc97.applydigital.repository

import com.gustavofc97.applydigital.data.entity.ArticleEntity
import com.gustavofc97.applydigital.data.entity.asExternalModel
import com.gustavofc97.applydigital.data.model.ArticleResource
import com.gustavofc97.applydigital.di.ApplyDispatchers
import com.gustavofc97.applydigital.di.Dispatcher
import com.gustavofc97.applydigital.repository.datasource.ArticlesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val dataSource: ArticlesDataSource,
    @Dispatcher(ApplyDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,

    ) : HomeRepository {
    override suspend fun loadData(): List<ArticleResource> = withContext(ioDispatcher)
    {
        dataSource.loadArticles().map(ArticleEntity::asExternalModel)
    }

    override suspend fun deleteArticle(articleResourceId: Int) = withContext(ioDispatcher) {
        dataSource.deleteArticle(articleResourceId)
    }

}
