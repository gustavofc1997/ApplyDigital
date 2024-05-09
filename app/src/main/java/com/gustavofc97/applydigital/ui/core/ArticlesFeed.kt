package com.gustavofc97.applydigital.ui.core

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gustavofc97.applydigital.data.model.ArticleResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleGridItem(
    articleResource: ArticleResource,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDelete: (Int) -> Unit,
) {

    val dismissState = rememberSwipeToDismissBoxState(confirmValueChange = {
        when (it) {
            SwipeToDismissBoxValue.EndToStart -> {
                onDelete.invoke(articleResource.id)
                false
            }

            else -> false
        }
    })

    SwipeToDismissBox(
        state = dismissState, modifier = modifier,
        backgroundContent = {},
        enableDismissFromEndToStart = true,
        enableDismissFromStartToEnd = false
    ) {

        Row(
            modifier
                .fillMaxWidth()
                .padding(
                    8.dp
                )
                .clickable {
                    onClick()
                }) {
            Column {
                Text(
                    text = articleResource.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
                Text(
                    text = articleResource.author,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1
                )
            }
        }
    }

}


fun launchCustomChromeTab(context: Context, uri: Uri) {
    val customTabBarColor = CustomTabColorSchemeParams.Builder().build()
    val customTabsIntent = CustomTabsIntent.Builder()
        .setDefaultColorSchemeParams(customTabBarColor)
        .build()

    customTabsIntent.launchUrl(context, uri)
}
