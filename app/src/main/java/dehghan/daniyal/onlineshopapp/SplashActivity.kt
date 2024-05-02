package dehghan.daniyal.onlineshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dehghan.daniyal.onlineshopapp.db.viewmodels.UserEntityViewModel
import dehghan.daniyal.onlineshopapp.ui.screens.SplashScreen
import dehghan.daniyal.onlineshopapp.ui.theme.OnlineShopAppTheme
import dehghan.daniyal.onlineshopapp.utils.ThisApp

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLoad by remember { mutableStateOf(false) }
            val userEntityViewModel =
                ViewModelProvider(this).get(UserEntityViewModel::class.java)
            userEntityViewModel.getCurrentUser().observe(this) {
                userEntityViewModel.currentUser.value = it
                isLoad = true
            }
            OnlineShopAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (userEntityViewModel.currentUser.value != null) {
                        ThisApp.token = userEntityViewModel.currentUser.value!!.token!!
                    }
                    if (isLoad)
                        SplashScreen(this, userEntityViewModel)
                }
            }
        }
    }
}
