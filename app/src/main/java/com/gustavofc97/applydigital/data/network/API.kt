package com.gustavofc97.applydigital.data.network

import com.gustavofc97.applydigital.data.model.GetArticlesResponse
import retrofit2.http.GET

interface API {

    @GET("search_by_date?query=mobile")
    suspend fun getArticles(): GetArticlesResponse

}