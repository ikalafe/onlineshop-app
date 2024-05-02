package dehghan.daniyal.onlineshopapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dehghan.daniyal.onlineshopapp.ui.components.LoadingInColumn
import dehghan.daniyal.onlineshopapp.ui.components.LoadingInRow
import dehghan.daniyal.onlineshopapp.ui.components.products.ProductListItemView
import dehghan.daniyal.onlineshopapp.viewmodels.products.ProductByCategoryViewModel

@Composable
fun ProductsScreen(
    categoryId: Long,
    title: String,
    navController: NavHostController,
    viewModel: ProductByCategoryViewModel = hiltViewModel()
) {
    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }

    Column {
        LazyColumn(Modifier.padding(20.dp, 0.dp)) {
            item {
                androidx.compose.animation.AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 500),
                        initialOffsetY = { -40 }) + fadeIn(tween(500, 500)),
                    exit = fadeOut()
                ) {
                    Text(text = title, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }
                    Spacer(modifier = Modifier.height(20.dp))

                }
            items(dataList.value.size) { index ->
                viewModel.onScrollPositionChange(index)
                if ((index + 1) >= (viewModel.pageIndex.value * viewModel.pageSize) && !viewModel.isLoading.value) {
                    viewModel.nextPage()
                }
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500,1000),
                        initialOffsetY = { -40 }) + fadeIn(tween(500,1000)),
                    exit = fadeOut()
                ) {
                    ProductListItemView(dataList.value[index], navController)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                )
                            )
                        )
                )
            }
            if (isLoading.value) {
                item {
                    LoadingInColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }
        }
    }
}