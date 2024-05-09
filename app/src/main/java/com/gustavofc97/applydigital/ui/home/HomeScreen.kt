package com.gustavofc97.applydigital.ui.home

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gustavofc97.applydigital.R
import com.gustavofc97.applydigital.data.model.ArticleResource
import com.gustavofc97.applydigital.ui.core.ArticleGridItem
import com.gustavofc97.applydigital.ui.core.ArticlesUiState
import com.gustavofc97.applydigital.ui.core.launchCustomChromeTab
import com.gustavofc97.applydigital.ui.core.linearGradientBrush

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val articlesState by homeViewModel.articlesUiState.collectAsState()

    HomeScreen(
        articlesState, { homeViewModel.loadArticles() }
    ) {
        homeViewModel.removeFromArticles(it)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    articlesUiState: ArticlesUiState,
    loadData: () -> Unit,
    onDelete: (Int) -> Unit,
) {
    LaunchedEffect(Unit) {
        loadData()
    }

    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) {
        loadData()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .nestedScroll(pullToRefreshState.nestedScrollConnection)
        ) {
            when (articlesUiState) {
                is ArticlesUiState.Success -> {
                    pullToRefreshState.endRefresh()
                    ArticlesList(
                        modifier = Modifier.fillMaxSize(),
                        articlesUiState.articles, onDelete
                    )
                }

                ArticlesUiState.EmptyResult -> {
                    pullToRefreshState.endRefresh()
                    EmptyArticlesScreen()
                }

                ArticlesUiState.Error -> {
                    pullToRefreshState.endRefresh()
                    ErrorScreen(onRetryClick = loadData)
                }

                else -> {}
            }
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullToRefreshState
            )
        }
    }
}

@Composable
fun EmptyArticlesScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.msg_empty_result)
            )
        }
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.msg_general_error)
            )
            Button(onClick = onRetryClick) {
                Text(text = stringResource(R.string.msg_retry))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<ArticleResource>,
    onDelete: (Int) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val scrollableState = rememberLazyListState()
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 12.dp),
            contentPadding = PaddingValues(vertical = 6.dp),
            state = scrollableState,
        ) {
            articles.forEach { articleItem ->
                item(key = articleItem.id) {
                    val context = LocalContext.current
                    ArticleGridItem(
                        modifier = Modifier
                            .animateItemPlacement()
                            .fillMaxWidth()
                            .then(linearGradientBrush),
                        articleResource = articleItem,
                        onClick = {
                            launchCustomChromeTab(
                                context, Uri.parse(articleItem.url)
                            )
                        }, onDelete = onDelete
                    )
                }
            }
        }
    }
}
