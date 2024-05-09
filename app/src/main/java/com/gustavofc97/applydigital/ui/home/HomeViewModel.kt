package com.gustavofc97.applydigital.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavofc97.applydigital.repository.HomeRepository
import com.gustavofc97.applydigital.ui.core.ArticlesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _articlesUiState =
        MutableStateFlow<ArticlesUiState>(ArticlesUiState.Loading)
    val articlesUiState: StateFlow<ArticlesUiState> get() = _articlesUiState

    fun loadArticles() {
        viewModelScope.launch {
            try {
                val result = homeRepository.loadData()
                if (result.isEmpty()) {
                    _articlesUiState.value = ArticlesUiState.EmptyResult
                } else {
                    _articlesUiState.value = ArticlesUiState.Success(result)
                }
            } catch (e: Exception) {
                _articlesUiState.value = ArticlesUiState.Error
            }
        }
    }

    fun removeFromArticles(articleResourceId: Int) {
        viewModelScope.launch {
            homeRepository.deleteArticle(articleResourceId)
            loadArticles()
        }
    }
}
