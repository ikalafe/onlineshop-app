package dehghan.daniyal.onlineshopapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dehghan.daniyal.onlineshopapp.db.viewmodels.UserEntityViewModel
import dehghan.daniyal.onlineshopapp.models.customer.UserVM
import dehghan.daniyal.onlineshopapp.ui.theme.Dark
import dehghan.daniyal.onlineshopapp.ui.theme.Purple40
import dehghan.daniyal.onlineshopapp.viewmodels.customers.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var username by remember { mutableStateOf(TextFieldValue()) }
    var usernameError by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    LazyColumn {
        item {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
        item {
            Column(
                Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Text(text = "Let's Sign You In.", fontSize = 28.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Welcome Back.", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "You've been Missed!", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(20.dp))


                OutlinedTextField(
                    value = username, onValueChange = {
                        username = it
                        usernameError = false
                    },
                    label = { Text(text = "User Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        if (usernameError) {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    },
                    isError = usernameError
                )
                if (usernameError) {
                    Text(
                        text = "Please Enter Your User Name!",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                //Password And Checked Valid Password
                OutlinedTextField(
                    value = password, onValueChange = {
                        password = it
                        passwordError = false
                    },
                    label = { Text(text = "Password") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        if (passwordError) {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    },
                    isError = passwordError
                )
                if (passwordError) {
                    Text(
                        text = "Please Enter Your Password!",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                if (isLoading) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Column {
                        Button(
                            onClick = {
                                if (username.text.isEmpty()) {
                                    usernameError = true
                                }
                                if (password.text.isEmpty()) {
                                    passwordError = true
                                }
                                if (usernameError || passwordError) return@Button
                                isLoading = true
                                userViewModel.login(
                                    UserVM(
                                        username = username.text,
                                        password = password.text
                                    )
                                ) { response ->
                                    if (response.status == "OK") {
                                        val user = response.data!![0]
                                        CoroutineScope(Dispatchers.IO).launch {
                                            userEntityViewModel.insert(user.convertToEntity())
                                        }
                                        Toast.makeText(
                                            context,
                                            "Welcome Back dear ${user.firstName}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        navController.navigate("home")
                                    }
                                    isLoading = false
                                }
                            },
                            shape = CircleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Dark
                            )
                        ) {
                            Text(
                                text = "Login",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = { navController.navigate("proceedToPayment") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Purple40, CircleShape),
                            colors = ButtonDefaults.buttonColors(Purple40),
                            shape = CircleShape
                        ) {
                            Text(text = "Register",color = Color.White)
                        }
                    }
                }
            }
        }
    }
}