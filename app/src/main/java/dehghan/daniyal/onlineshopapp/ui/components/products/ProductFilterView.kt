package dehghan.daniyal.onlineshopapp.ui.components.products

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dehghan.daniyal.onlineshopapp.viewmodels.products.ProductViewModel

@Composable
fun ProductFilterView(
    viewModel: ProductViewModel = hiltViewModel(),
) {
    val filters = listOf("All", "New", "Popular")
    var selectedFilter by rememberSaveable { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        viewModel.getProducts(0, 6) {}
    }
    LazyRow {
        items(filters.size) { index ->
            TextButton(
                onClick = {
                        selectedFilter = index
                        when (selectedFilter) {
                            0 -> {
                                viewModel.getProducts(0, 6) { response ->
                                    if (response.status == "OK".uppercase()) {
                                        viewModel.dataList.value = response.data!!
                                    }
                                }
                            }

                            1 -> {
                                viewModel.getNewProduct {}
                            }

                            2 -> {
                                viewModel.getPopularProducts {}
                            }
                        }
                },
                colors = ButtonDefaults.buttonColors(
                    //TODO:Change Color
                    containerColor = if (selectedFilter == index) Color.LightGray
                    else {
                        Color.Transparent
                    }
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.width(90.dp)
            ) {
                Text(
                    text = filters[index],
                    color = if (isSystemInDarkTheme() && selectedFilter != index) Color.White else Color.Black,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}