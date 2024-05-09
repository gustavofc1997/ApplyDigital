package com.gustavofc97.applydigital.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gustavofc97.applydigital.data.model.ArticleResource

@Entity(tableName = "Article")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val url: String,
    val date: String,
    val author: String,
    val visible: Boolean = true
)

fun ArticleEntity.asExternalModel() = ArticleResource(
    id, title, url, date, author
)
