package dehghan.daniyal.onlineshopapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dehghan.daniyal.onlineshopapp.ui.components.LoadingInColumn
import dehghan.daniyal.onlineshopapp.ui.components.LoadingInRow
import dehghan.daniyal.onlineshopapp.ui.components.products.ProductCategoryListView
import dehghan.daniyal.onlineshopapp.ui.components.products.ProductFilterView
import dehghan.daniyal.onlineshopapp.ui.components.products.ProductListItemView
import dehghan.daniyal.onlineshopapp.ui.components.sliders.SliderListView
import dehghan.daniyal.onlineshopapp.viewmodels.products.ProductViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: ProductViewModel = hiltViewModel()) {
    var productList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }

    LazyColumn(modifier = Modifier.padding(20.dp, 0.dp)) {
        item {
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(500, 1000),
                    initialOffsetY = { -40 }) + fadeIn(tween(500, 1000)),
                exit = fadeOut()
            ) {
                SliderListView()
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(500,1500),
                    initialOffsetY = { -40 }) + fadeIn(tween(500,1500)),
                exit = fadeOut()
            ) {
                ProductCategoryListView(navController = navController)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(500,2000),
                    initialOffsetY = { -40 }) + fadeIn(tween(500,2000)),
                exit = fadeOut()
            ) {
                ProductFilterView()
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        if (isLoading.value) {
            item {
                LoadingInColumn(
                    Modifier
                        .fillMaxSize()
                        .height(200.dp)
                )
            }
        } else {

            items(productList.value.size) { index ->
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500,2500),
                        initialOffsetY = { -40 }) + fadeIn(tween(500,2500)),
                    exit = fadeOut()
                ) {
                    ProductListItemView(productList.value[index], navController)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}


//
//https://media.architecturaldigest.com/photos/57a11cbeb6c434ab487bc26b/16:9/w_1280,c_limit/nikes-senior-designer-explains-what-went-into-new-air-jordan-01.png
//https://holosen.net/wp-content/uploads/2021/10/sn_01.jpg    ok
//https://robbreport.com/wp-content/uploads/2022/10/RR_Best_Jordan_Sneakers_VII_OG_White_Black_Taxi.jpg?w=1000    ok
//https://sneakernews.com/wp-content/uploads/2024/02/jumpman-jack.jpg    ok
//https://www.soleretriever.com/_next/image?url=https%3A%2F%2Fwnqjelklwcclokznfoyh.supabase.co%2Fstorage%2Fv1%2Fobject%2Fpublic%2Fgeneral%2Fjordan_brand.jpeg&w=768&q=75
//https://holosen.net/wp-content/uploads/2021/10/hoodie.jpg   ok
//https://holosen.net/wp-content/uploads/2021/10/ho_01.jpg    ok
//https://holosen.net/wp-content/uploads/2021/10/ho_02.jpg    ok
//https://holosen.net/wp-content/uploads/2021/10/ho_03.jpg      ok
//https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/3152488c-25b6-4c81-b794-22a5c00de154/sportswear-club-fleece-big-kids-cargo-pants-extended-size-bW30Bf.png     ok
//https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrfAj-QgHRV4fLhDO3-zB1n-5hqwxVP-yyFzDNHZt6iw&s      ok
//https://balenciaga.dam.kering.com/m/7e8b8dcfd5fb15ef/Medium-767971TNW111672_H.jpg?v=1      ok
//https://www.babimod.com/wp-content/uploads/2023/11/Slash-bag-style-pants-with-elastic-waist-NIKE-code-67631-3.jpg      ok