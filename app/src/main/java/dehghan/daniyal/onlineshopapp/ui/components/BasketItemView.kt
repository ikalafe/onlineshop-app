package dehghan.daniyal.onlineshopapp.ui.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import dehghan.daniyal.onlineshopapp.db.models.BasketEntity
import dehghan.daniyal.onlineshopapp.db.viewmodels.BasketEntityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketItemView(
    basketEntity: BasketEntity,
    viewModel: BasketEntityViewModel,
    totalPrice: MutableState<Long>,
    navController: NavController
) {
    var quantity by remember { mutableStateOf(basketEntity.quntity) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 10.dp, 20.dp, 0.dp),
    ) {
        Card(
            modifier = Modifier
                .size(100.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(20.dp),
                    clip = true
                ),
            shape = RoundedCornerShape(20.dp),
            onClick = {
                navController.navigate("showProduct/${basketEntity.productId}")
            }
        ) {
            GlideImage(
                imageModel = { basketEntity.image },
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
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Column {
                Text(
                    text = basketEntity.title!!,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${basketEntity.price!!} T",
                    color = Color.Gray,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            Spacer(modifier = Modifier.height(7.dp))
            Row {
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.decrementQuantity(basketEntity)
                    }
                    quantity--
                    totalPrice.value -= basketEntity.price!!
                }, Modifier.size(30.dp)) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null)
                }
                Spacer(modifier = Modifier.width(10.dp))

                Text(text = quantity.toString(), fontSize = 21.sp)

                Spacer(modifier = Modifier.width(10.dp))
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.incrementQuantity(basketEntity)
                    }
                    quantity++
                    totalPrice.value += basketEntity.price!!
                }, Modifier.size(30.dp)) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = null)
                }
                Spacer(modifier = Modifier.width(40.dp))
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.delete(basketEntity)
                    }
                }, Modifier.size(30.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = basketEntity.size, fontSize = 20.sp, color = Color.Gray)
    }
    Spacer(modifier = Modifier.height(30.dp))
}