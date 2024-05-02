package dehghan.daniyal.onlineshopapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dehghan.daniyal.onlineshopapp.db.viewmodels.UserEntityViewModel
import dehghan.daniyal.onlineshopapp.models.customer.UserVM
import dehghan.daniyal.onlineshopapp.ui.theme.Dark
import dehghan.daniyal.onlineshopapp.utils.ThisApp
import dehghan.daniyal.onlineshopapp.viewmodels.customers.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val currentUser by remember { mutableStateOf(userEntityViewModel.currentUser) }
    val isLoggedIn by remember { mutableStateOf(userEntityViewModel.currentUser.value != null) }

    var firstName by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.firstName!! else "")) }
    var firstNameError by remember { mutableStateOf(false) }

    var lastName by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.lastName!! else "")) }
    var lastNameError by remember { mutableStateOf(false) }

    var phone by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.phone!! else "")) }
    var phoneError by remember { mutableStateOf(false) }

    var address by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.address!! else "")) }
    var addressError by remember { mutableStateOf(false) }

    var username by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.username!! else "")) }
    var usernameError by remember { mutableStateOf(false) }

    var postalCode by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.postalCode!! else "")) }
    var postalCodeError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    Column {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
            Text(
                text = "Complete Your Information",
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
            )
        }
        LazyColumn {
            item {
                Column(Modifier.padding(20.dp)) {
                    //Field First Name And Checked Valid First Name
                    OutlinedTextField(
                        value = firstName, onValueChange = {
                            firstName = it
                            firstNameError = false
                        },
                        label = { Text(text = "First Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        trailingIcon = {
                            if (firstNameError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        },
                        isError = firstNameError
                    )
                    if (firstNameError) {
                        Text(
                            text = "Please Enter Your First Name!",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    //Last First Name And Checked Valid Last Name
                    OutlinedTextField(
                        value = lastName, onValueChange = {
                            lastName = it
                            lastNameError = false
                        },
                        label = { Text(text = "Last Name") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        singleLine = true,
                        trailingIcon = {
                            if (lastNameError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        },
                        isError = lastNameError
                    )
                    if (lastNameError) {
                        Text(
                            text = "Please Enter Your Last Name!",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    //Password And Checked Valid Password
                    OutlinedTextField(
                        value = phone, onValueChange = {
                            phone = it
                            phoneError = false
                        },
                        label = { Text(text = "Phone") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Next
                        ),
                        trailingIcon = {
                            if (phoneError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        },
                        isError = phoneError
                    )
                    if (phoneError) {
                        Text(
                            text = "Please Enter Your Phone!",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    //User Name And Checked Valid User Name
                    OutlinedTextField(
                        value = username, onValueChange = {
                            username = it
                            usernameError = false
                        },
                        label = { Text(text = "User Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        enabled = currentUser.value == null,
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
                    //Postal Code Field And Checked Valid Postal Code
                    OutlinedTextField(
                        value = postalCode, onValueChange = {
                            postalCode = it
                            postalCodeError = false
                        },
                        label = { Text(text = "Postal Code") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        trailingIcon = {
                            if (postalCodeError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        },
                        isError = postalCodeError
                    )
                    if (postalCodeError) {
                        Text(
                            text = "Please Enter Your Postal Code!",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    //Address And Checked Valid Address
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = address, onValueChange = {
                            address = it
                            addressError = false
                        },
                        label = { Text(text = "Address") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            if (addressError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        },
                        isError = addressError
                    )
                    if (addressError) {
                        Text(
                            text = "Please Enter Your Address!",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    if (!isLoading) {
                        Button(
                            onClick = {
                                if (firstName.text.isEmpty()) {
                                    firstNameError = true
                                }
                                if (lastName.text.isEmpty()) {
                                    lastNameError = true
                                }
                                if (username.text.isEmpty()) {
                                    usernameError = true
                                }
                                if (phone.text.isEmpty()) {
                                    phoneError = true
                                }
                                if (address.text.isEmpty()) {
                                    addressError = true
                                }
                                if (postalCode.text.isEmpty()) {
                                    postalCodeError = true
                                }
                                if (firstNameError || lastNameError || usernameError || phoneError || addressError || postalCodeError) {
                                    return@Button
                                }
                                var userInfo = UserVM(
                                    id = currentUser.value!!.userId,
                                    customerId = currentUser.value!!.customerId,
                                    username = username.text,
                                    firstName = firstName.text,
                                    lastName = lastName.text,
                                    phone = phone.text,
                                    address = address.text,
                                    postalCode = postalCode.text,
                                )
                                isLoading = true
                                userViewModel.update(userInfo) { response ->
                                    if (response.status == "OK") {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            val userEntity = userInfo.convertToEntity()
                                            userEntity.id = currentUser.value!!.id
                                            userEntity.token = currentUser.value!!.token!!
                                            userEntityViewModel.update(userEntity)
                                        }
                                        navController.popBackStack()
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
                            ),

                            ) {
                            Text(
                                text = "Update Proile",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
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
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}