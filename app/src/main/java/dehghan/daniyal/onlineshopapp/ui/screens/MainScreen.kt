package dehghan.daniyal.onlineshopapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dehghan.daniyal.onlineshopapp.MainActivity
import dehghan.daniyal.onlineshopapp.db.viewmodels.BasketEntityViewModel
import dehghan.daniyal.onlineshopapp.db.viewmodels.UserEntityViewModel
import dehghan.daniyal.onlineshopapp.ui.components.TopAppView
import dehghan.daniyal.onlineshopapp.utils.ThisApp

@Composable
fun MainScreen(mainActivity: MainActivity) {

    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }
    val basketViewModel =
        ViewModelProvider(mainActivity).get(BasketEntityViewModel::class.java)
    val userEntityViewModel =
        ViewModelProvider(mainActivity).get(UserEntityViewModel::class.java)
    basketViewModel.getAllBasketLive().observe(mainActivity) {
        basketViewModel.dataList.value = it
    }
    userEntityViewModel.getCurrentUser().observe(mainActivity) {
        userEntityViewModel.currentUser.value = it
    }
    Scaffold(
        topBar = {
            if (!fullScreen)
                TopAppView(navController, basketViewModel,userEntityViewModel)
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            startDestination = "home",
            navController = navController
        ) {
            composable("home") {
                fullScreen = false
                HomeScreen(navController)
            }
            composable("basket") {
                fullScreen = true
                BasketListScreen(navController, basketViewModel)
            }
            composable("proceedToPayment") {
                fullScreen = true
                UserPaymentScreen(
                    navController = navController,
                    basketViewModel = basketViewModel,
                    mainActivity = mainActivity,
                    userEntityViewModel = userEntityViewModel
                )
            }
            composable(
                "products/{categoryId}/{title}",
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.LongType },
                    navArgument("title") { type = NavType.StringType }
                )
            ) { backStack ->
                fullScreen = false
                val id = backStack.arguments?.getLong("categoryId")
                val title = backStack.arguments?.getString("title")
                ThisApp.productCategoryId = id!!
                ProductsScreen(id, title!!, navController)
            }
            composable(
                "showProduct/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.LongType })
            ) { backStack ->
                fullScreen = true
                backStack.arguments?.getLong("productId").let {
                    ShowProductScreen(it!!, navController, basketViewModel)
                }
            }
            composable(
                "invoice/{id}",
//                deepLinks = listOf(navDeepLink { uriPattern = "app://onlineshopholosen.ir" }),
//                arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) {
//                InvoiceScreen(navController, backStackEntry.arguments?.getLong("id")!!)
            }
            composable("login") {
                fullScreen = true
                LoginScreen(navController,userEntityViewModel)
            }
            composable("dashboard") {
                fullScreen = true
                DashboardScreen(navController,userEntityViewModel)
            }
            composable("invoices") {
                fullScreen = true
                InvoiceListScreen(navController)
            }
            composable("editProfile") {
                fullScreen = true
                EditProfileScreen(navController,userEntityViewModel)
            }
            composable("changePassword") {
                fullScreen = true
                ChangePasswordScreen(navController,userEntityViewModel)
            }
        }
    }
}