package uz.itschool.docc.screen.registration

import android.widget.Button
import androidx.compose.foundation.Image
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo_horizontal),
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 50.dp)
                .size(150.dp)
        )
        Text(
            text = "Hisob yaratish",
            fontSize = 40.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 30.dp)
        )
        Text(
            text = "Shaxsiy ma’lumotlaringizni kiriting",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 10.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            shape = RoundedCornerShape(
                topEnd = 15.dp,
                topStart = 15.dp,
                bottomEnd = 15.dp,
                bottomStart = 15.dp
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.person_icon),
                    contentDescription = "",
                    Modifier.size(25.dp)
                )
            },
            label = {
                Text(
                    "Ism va familiya",
                    color = Color(0xFF909090),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp
                )
            },
            placeholder = {
                Text(
                    text = "Ism va familiya",
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
                .padding(top = 50.dp),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
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
            supportingText = {
                Text(
                    "Bu telefon raqamga kod jo’natiladi",
                    color = Color(0xFFFF0000),
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
            supportingText = {
                Text(
                    "8 ta elementli yangi parol kiriting",
                    color = Color(0xFFFF0000),
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

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8D7BFD)),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 80.dp),
        ) {
            Text(
                text = "Ro’yxatdan o’tish",
                color = Color(0xFFFFFFFF),
                style = MaterialTheme.typography.bodySmall,
                fontSize = 20.sp
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
        ) {
            Column(modifier = Modifier
                .align(Alignment.CenterHorizontally)){
                Row {
                    Text(
                        text = "Akkauntingiz bormi?",
                        fontSize = 18.sp,
                        color = Color(0xFF909090),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )
                    Text(
                        text = "Kirish",
                        fontSize = 18.sp,
                        color = Color(0xFF8D7BFD),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(top = 20.dp, start = 5.dp)
                    )
                }
            }
        }
    }
}