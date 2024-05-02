package dehghan.daniyal.onlineshopapp.ui.components.products

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dehghan.daniyal.onlineshopapp.ui.components.LoadingInRow
import dehghan.daniyal.onlineshopapp.viewmodels.products.ProductCategoryViewModel

@Composable
fun ProductCategoryListView(
    navController: NavController,
    viewModel: ProductCategoryViewModel = hiltViewModel()
) {

    val dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value) {
        LoadingInRow(modifier = Modifier
            .width(160.dp)
            .height(200.dp), 3)
    } else {
        LazyRow {
            items(dataList.value.size) { index ->
                ProductCategoryItemView(dataList.value[index], navController)
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}