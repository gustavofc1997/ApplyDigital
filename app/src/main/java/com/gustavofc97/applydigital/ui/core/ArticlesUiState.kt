package com.gustavofc97.applydigital.ui.core

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.gustavofc97.applydigital.data.model.ArticleResource

sealed interface ArticlesUiState {

    data object Loading : ArticlesUiState
    data object Error : ArticlesUiState
    data object EmptyResult : ArticlesUiState

    data class Success(
        val articles: List<ArticleResource>,
    ) : ArticlesUiState
}


val linearGradientBrush = Modifier.composed {
    this.then(
        Modifier.border(
            width = 2.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.tertiary
                )
            ),
            RoundedCornerShape(4.dp)
        )
    )
}
