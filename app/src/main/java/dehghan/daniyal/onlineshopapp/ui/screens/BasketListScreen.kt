package dehghan.daniyal.onlineshopapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dehghan.daniyal.onlineshopapp.db.viewmodels.BasketEntityViewModel
import dehghan.daniyal.onlineshopapp.ui.components.BasketItemView
import dehghan.daniyal.onlineshopapp.ui.theme.Dark

@Composable
fun BasketListScreen(navController: NavHostController, basketViewModel: BasketEntityViewModel) {

    var dataList by remember { mutableStateOf(basketViewModel.dataList) }
    var totalPriceLong: Long = 0

    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }

    for (item in dataList.value) {
        totalPriceLong += item.quntity.toLong() * item.price!!
    }
    var totalPrice = remember { mutableStateOf(totalPriceLong) }

    LazyColumn {
        item {
            Row {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
                Column(modifier = Modifier.padding(0.dp, 9.dp, 0.dp, 0.dp)) {
                    Spacer(modifier = Modifier.width(5.dp))
                    androidx.compose.animation.AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500, 500),
                            initialOffsetY = { -40 }) + fadeIn(tween(500, 500)),
                        exit = fadeOut()
                    ) {
                        Text(text = "Shopping Cart", textAlign = TextAlign.Center, fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    if (dataList.value.isNotEmpty()) {
                        AnimatedVisibility(
                            visibleState = animatedVisibleState,
                            enter = slideInVertically(
                                animationSpec = tween(500,1000),
                                initialOffsetY = { -40 }) + fadeIn(tween(500,1000)),
                            exit = fadeOut()
                        ) {
                            Text(
                                text = "${dataList.value.size} Items",
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
        if (dataList.value.isEmpty()) {
            item {
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500,1500),
                        initialOffsetY = { -40 }) + fadeIn(tween(500,1500)),
                    exit = fadeOut()
                ) {
                    Column(
                        Modifier
                            .padding(20.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(text = "Your Shopping Cart is Empty!", fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(50.dp))
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = null,
                            Modifier.size(200.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
        items(dataList.value.size) { index ->
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(500,1500),
                    initialOffsetY = { -40 }) + fadeIn(tween(500,1500)),
                exit = fadeOut()
            ) {
                BasketItemView(dataList.value[index], basketViewModel, totalPrice, navController)
            }
        }
        item {
            if (dataList.value.isNotEmpty()) {
                Spacer(modifier = Modifier.height(15.dp))
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500,2000),
                        initialOffsetY = { -40 }) + fadeIn(tween(500,2000)),
                    exit = fadeOut()
                ) {
                    Row {
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "Total Price: ", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text(
                            text = totalPrice.value.toString(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500,2500),
                        initialOffsetY = { -40 }) + fadeIn(tween(500,2500)),
                    exit = fadeOut()
                ) {
                    Column(Modifier.padding(20.dp)) {
                        Button(
                            onClick = {
                                navController.navigate("proceedToPayment")
                            },
                            shape = CircleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Dark
                            ),

                            ) {
                            Text(
                                text = "Proceed To Payment",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}