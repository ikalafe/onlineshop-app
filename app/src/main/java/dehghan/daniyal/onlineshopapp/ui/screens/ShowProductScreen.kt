package dehghan.daniyal.onlineshopapp.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import dehghan.daniyal.onlineshopapp.db.models.BasketEntity
import dehghan.daniyal.onlineshopapp.db.viewmodels.BasketEntityViewModel
import dehghan.daniyal.onlineshopapp.ui.components.LoadingInColumn
import dehghan.daniyal.onlineshopapp.ui.theme.Dark
import dehghan.daniyal.onlineshopapp.ui.theme.Pink40
import dehghan.daniyal.onlineshopapp.ui.theme.Pink80
import dehghan.daniyal.onlineshopapp.ui.theme.Purple40
import dehghan.daniyal.onlineshopapp.ui.theme.Purple80
import dehghan.daniyal.onlineshopapp.ui.theme.PurpleGrey40
import dehghan.daniyal.onlineshopapp.ui.theme.PurpleGrey80
import dehghan.daniyal.onlineshopapp.ui.theme.Red
import dehghan.daniyal.onlineshopapp.viewmodels.products.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowProductScreen(
    productId: Long,
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel,
    viewModel: ProductViewModel = hiltViewModel(),
) {
    var data by remember { mutableStateOf(viewModel.data) }
    var isLoading by remember { mutableStateOf(true) }
    var selectedSize by remember { mutableStateOf(0) }
    var selectedColor by remember { mutableStateOf(0) }
    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }
    var indexColor: List<Color> =
        listOf(Purple80, PurpleGrey80, Pink80, Purple40, PurpleGrey40, Pink40, Red, Color.Red,
            Color.Green, PurpleGrey40, Dark)
    val context = LocalContext.current
    viewModel.getProductById(productId) { response ->
        isLoading = false
        if (response.status == "OK") {
            if (response.data!!.isNotEmpty()) {
                viewModel.data.value = response.data!![0]
            } else {
                Toast.makeText(context, "Error on load data!", Toast.LENGTH_LONG).show()
                navController.popBackStack()
            }
        }
    }
    if (isLoading) {
        LoadingInColumn(modifier = Modifier.fillMaxSize())
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Box {
                GlideImage(
                    imageModel = { data.value!!.image!! },
                    modifier = Modifier.fillMaxSize(),
                    loading = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    failure = {
                        Text(text = "image request failed.")
                    }
                )
            }
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                contentAlignment = Alignment.TopStart
            ) {
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500,500),
                        initialOffsetY = { -40 }) + fadeIn(tween(500,500)),
                    exit = fadeOut()
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .background(
                                color = Color.Black,
                                shape = CircleShape,
                            )
                            .padding(2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500,1000),
                            initialOffsetY = { -40 }) + fadeIn(tween(500,1000)),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = data.value!!.title!!,
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500,1200),
                            initialOffsetY = { -40 }) + fadeIn(tween(500,1200)),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = "${data.value!!.price!!} IRT",
                            color = Color.White,
                            fontSize = 26.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500,1400),
                            initialOffsetY = { -40 }) + fadeIn(tween(500,1400)),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = "Sizes",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500,1600),
                            initialOffsetY = { -40 }) + fadeIn(tween(500,1600)),
                        exit = fadeOut()
                    ) {
                        LazyRow {
                            items(data.value!!.sizes!!.size) { index ->
                                TextButton(
                                    onClick = {
                                        selectedSize = index
                                    },
                                    shape = CircleShape,
                                    colors = ButtonDefaults.buttonColors(containerColor = if (selectedSize == index) Color.White else Color.Transparent),
                                    modifier = Modifier.size(50.dp)
                                ) {
                                    Text(
                                        text = data.value!!.sizes!![index].title!!,
                                        fontWeight = FontWeight.Bold,
                                        color = if (selectedSize == index) Color.Black else Color.White
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500,1800),
                            initialOffsetY = { -40 }) + fadeIn(tween(500,1800)),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = "Colors",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500,2000),
                            initialOffsetY = { -40 }) + fadeIn(tween(500,2000)),
                        exit = fadeOut()
                    ) {
                        LazyRow {
                            items(data.value!!.colors!!.size) { index ->
                                TextButton(
                                    onClick = {
                                        selectedColor = index
                                    },
                                    shape = CircleShape,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = indexColor[index]
                                    ),
                                    modifier = Modifier.size(40.dp),
                                    border = BorderStroke(1.dp, Color.White)
                                ) {
                                    if (selectedColor == index) {
                                        Icon(
                                            imageVector = Icons.Filled.Check,
                                            contentDescription = null
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500,2200),
                            initialOffsetY = { -40 }) + fadeIn(tween(500,2200)),
                        exit = fadeOut()
                    ) {
                        Button(
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val basket = BasketEntity(
                                        productId = productId,
                                        quntity = 1,
                                        sizeId = if (data.value!!.sizes!!.isNotEmpty()) data.value!!.sizes!![selectedSize].id!! else null,
                                        colorId = if (data.value!!.colors!!.isNotEmpty()) data.value!!.colors!![selectedColor].id!! else null,
                                        image = data.value!!.image,
                                        price = data.value!!.price,
                                        title = data.value!!.title,
                                        size = data.value!!.sizes?.get(selectedSize)!!.title!!
                                    )
                                    basketViewModel.addToBasket(basket)
                                }
                                Toast.makeText(
                                    context,
                                    "Product Added To Your Basket!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.popBackStack()
                            },
                            shape = CircleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),

                            ) {
                            Text(
                                text = "Buy Now",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}