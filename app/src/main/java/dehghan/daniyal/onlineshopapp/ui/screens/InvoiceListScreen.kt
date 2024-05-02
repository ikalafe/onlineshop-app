package dehghan.daniyal.onlineshopapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dehghan.daniyal.onlineshopapp.ui.components.LoadingInColumn
import dehghan.daniyal.onlineshopapp.ui.components.invoices.InvoiceListItemView
import dehghan.daniyal.onlineshopapp.viewmodels.invoices.InvoiceViewModel

@Composable
fun InvoiceListScreen(
    navController: NavController,
    viewModel: InvoiceViewModel = hiltViewModel()
) {
    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    Column {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
            Column(modifier = Modifier.padding(0.dp, 9.dp, 0.dp, 0.dp)) {
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Invoices", textAlign = TextAlign.Center, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

        LazyColumn(Modifier.padding(20.dp, 0.dp)) {
            item {

            }
            items(dataList.value.size) { index ->
                viewModel.onScrollPositionChange(index)
                if ((index + 1) >= (viewModel.pageIndex.value * viewModel.pageSize) && !viewModel.isLoading.value) {
                    viewModel.nextPage()
                }
                InvoiceListItemView(dataList.value[index], navController)
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