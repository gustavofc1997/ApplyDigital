package com.gustavofc97.applydigital.data.model

import com.gustavofc97.applydigital.data.entity.ArticleEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class GetArticlesResponse(
    val hits: List<NetworkArticleResource>
)

@JsonClass(generateAdapter = true)
data class NetworkArticleResource(
    @Json(name = "story_title")
    val storyTitle: String?,
    @Json(name = "story_url")
    val storyUrl: String?,
    @Json(name = "story_id")
    val id: Int,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "author")
    val author: String,
    val title: String?,
    val url: String?,
) {
    fun toEntity() =
        ArticleEntity(
            id, (title ?: storyTitle).toString(), (storyUrl ?: url).toString(), updatedAt, author
        )
}


