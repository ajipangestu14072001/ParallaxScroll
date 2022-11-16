package trenggalek.dev.parallaxscroll.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun TopAppBar(
    backgroundAlpha: Float,
    navigationIcon: ImageVector = Icons.Default.ArrowBack,
    navigationIconTint: Color = Color.White,
    onNavigationIconClicked: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
    ) {
        val (backgroundRef, arrowRef) = createRefs()
        Surface(
            color = MaterialTheme.colors.primarySurface,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = backgroundAlpha
                }
                .constrainAs(backgroundRef) {
                    linkTo(
                        start = parent.start,
                        top = parent.top,
                        end = parent.end,
                        bottom = parent.bottom
                    )
                }
        ) {}
        IconButton(
            onClick = { onNavigationIconClicked() },
            modifier = Modifier
                .padding(start = 4.dp)
                .constrainAs(arrowRef) {
                    top.linkTo(backgroundRef.top)
                    bottom.linkTo(backgroundRef.bottom)
                    start.linkTo(parent.start)
                }
        ) {
            Icon(
                imageVector = navigationIcon,
                tint = navigationIconTint,
                contentDescription = "Navigation back arrow"
            )
        }
    }
}