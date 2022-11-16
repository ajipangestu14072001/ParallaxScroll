package trenggalek.dev.parallaxscroll.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import trenggalek.dev.parallaxscroll.R

@ExperimentalFoundationApi
@Composable
fun MainScreen(items: List<String>) {

    val lazyListState = rememberLazyListState()
    val visibility by remember {
        derivedStateOf {
            when {
                lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() && lazyListState.firstVisibleItemIndex == 0 -> {
                    val imageSize = lazyListState.layoutInfo.visibleItemsInfo[0].size
                    val scrollOffset = lazyListState.firstVisibleItemScrollOffset

                    scrollOffset / imageSize.toFloat()
                }
                else -> 1f
            }
        }
    }
    val firstItemTranslationY by remember {
        derivedStateOf {
            when {
                lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() && lazyListState.firstVisibleItemIndex == 0 -> lazyListState.firstVisibleItemScrollOffset * .6f
                else -> 0f
            }
        }
    }

    Box {
        LazyColumn(state = lazyListState) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.`as`),
                    contentDescription = "Landscape in BW",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .graphicsLayer {
                            alpha = 1f - visibility
                            translationY = firstItemTranslationY
                        }
                )
            }
            itemsIndexed(items) { idx, item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.surface)
                        .padding(8.dp)
                )

                if (idx < items.size - 1) {
                    Divider(color = Color.LightGray)
                }
            }
        }

        TopAppBar(backgroundAlpha = visibility)
    }
}
