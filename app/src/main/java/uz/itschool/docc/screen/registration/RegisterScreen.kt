package uz.itschool.docc.screen.registration

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.itschool.docc.R
import uz.itschool.docc.navigation.ScreenType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(
            text = "Xush kelibsiz!",
            fontSize = 40.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 100.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.reg_img),
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 30.dp)
                .size(200.dp)
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            shape = RoundedCornerShape(
                topEnd = 15.dp,
                topStart = 15.dp,
                bottomEnd = 15.dp,
                bottomStart = 15.dp
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.phone_icon),
                    contentDescription = "",
                    Modifier.size(30.dp)
                )
            },
            label = {
                Text(
                    "Telefon raqam",
                    color = Color(0xFF909090),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp
                )
            },
            placeholder = {
                Text(
                    text = "Telefon raqam",
                    color = Color(0xFF909090),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                keyboardType = KeyboardType.Phone
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF8D7BFD),
                unfocusedBorderColor = Color(0xFF909090)
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 25.dp),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            shape = RoundedCornerShape(
                topEnd = 15.dp,
                topStart = 15.dp,
                bottomEnd = 15.dp,
                bottomStart = 15.dp
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.password_icon),
                    contentDescription = "",
                    Modifier.size(25.dp)
                )
            },
            label = {
                Text(
                    "Parol",
                    color = Color(0xFF909090),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp
                )
            },
            placeholder = {
                Text(
                    text = "Parol",
                    color = Color(0xFF909090),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF8D7BFD),
                unfocusedBorderColor = Color(0xFF909090)
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 15.dp),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
        Column(modifier = Modifier.fillMaxWidth(0.8f)) {
            Text(
                text = "Parolni unutdingizmi?",
                fontSize = 18.sp,
                color = Color(0xFF8D7BFD),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .align(Alignment.End)
            )
        }

        Button(
            onClick = {
                navController.navigate(
                    ScreenType.ChatScreen.route
                )
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8D7BFD)),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 100.dp),
        ) {
            Text(
                text = "Kirish",
                color = Color(0xFFFFFFFF),
                style = MaterialTheme.typography.bodySmall,
                fontSize = 20.sp
            )
        }
        Column(modifier = Modifier.fillMaxWidth(0.8f)) {
            Row {
                Text(
                    text = "Akkauntingiz yo’qmi?",
                    fontSize = 18.sp,
                    color = Color(0xFF909090),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(top = 20.dp)
                )
                Text(
                    text = "Ro’yxatdan o’tish",
                    fontSize = 18.sp,
                    color = Color(0xFF8D7BFD),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(top = 20.dp, start = 5.dp)
                        .clickable {
                            navController.navigate(
                                ScreenType.SignUpScreen.route
                            )
                        }
                )
            }

        }
    }
}