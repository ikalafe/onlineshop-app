package dehghan.daniyal.onlineshopapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dehghan.daniyal.onlineshopapp.db.viewmodels.UserEntityViewModel
import dehghan.daniyal.onlineshopapp.ui.components.AdvanceButton
import dehghan.daniyal.onlineshopapp.ui.theme.Aqua
import dehghan.daniyal.onlineshopapp.ui.theme.LightBlue
import dehghan.daniyal.onlineshopapp.ui.theme.Orange
import dehghan.daniyal.onlineshopapp.utils.ThisApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(navController: NavHostController, userEntityViewModel: UserEntityViewModel) {
    val currentUser = userEntityViewModel.currentUser.value

    if (currentUser == null) {
        // هدایت کاربر به صفحه ورود یا صفحه خانه
        LaunchedEffect(Unit) {
            navController.navigate("login") {
                popUpTo("dashboard") { inclusive = true }
            }
        }
        return // برای جلوگیری از رندر بخش‌های بعدی UI
    }

    Column {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
            Column(modifier = Modifier.padding(0.dp, 9.dp, 0.dp, 0.dp)) {
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "User Profile", textAlign = TextAlign.Center, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

        Row(modifier = Modifier.padding(20.dp)) {
            Card(
                modifier = Modifier
                    .padding(0.dp)
                    .background(Color.LightGray, RoundedCornerShape(25.dp)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${currentUser.firstName} ${currentUser.lastName}",
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${currentUser.username}",
                    fontSize = 18.sp,
                    color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray
                )
            }
            //Edit Profile
            IconButton(onClick = {
                ThisApp.token = currentUser.token!!
                navController.navigate("editProfile")
            }) {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Account", Modifier.padding(20.dp), fontSize = 22.sp)
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            //Invoice
            item {
                AdvanceButton(
                    title = "Invoice",
                    subTitle = "Show Your Invoices",
                    imageVector = Icons.Outlined.Star,
                    iconBackgroundColor = LightBlue
                ) {
                    navController.navigate("invoices")
                }
                Spacer(modifier = Modifier.height(20.dp))
                //About
                AdvanceButton(
                    title = "Change Password",
                    subTitle = "Change Your Password",
                    imageVector = Icons.Outlined.Lock,
                    iconBackgroundColor = Aqua
                ) {
                    navController.navigate("changePassword")
                }
                Spacer(modifier = Modifier.height(20.dp))
                //Help
                AdvanceButton(
                    title = "Help",
                    subTitle = "Help And Feedback",
                    imageVector = Icons.Outlined.Phone,
                    iconBackgroundColor = Orange
                ) {

                }
                Spacer(modifier = Modifier.height(20.dp))
                //Logout
                AdvanceButton(
                    title = "Logout",
                    subTitle = "Logout Your Account",
                    imageVector = Icons.Outlined.ExitToApp,
                    iconBackgroundColor = Color.Red
                ) {
                    CoroutineScope(Dispatchers.IO).launch {
                        userEntityViewModel.deleteAll()
                    }
                    navController.navigate("home")
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

