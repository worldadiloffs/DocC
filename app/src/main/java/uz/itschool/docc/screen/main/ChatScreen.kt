package uz.itschool.docc.screen.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import uz.itschool.docc.R
import uz.itschool.docc.chat.data.Conversation
import uz.itschool.docc.chat.data.Message
import uz.itschool.docc.chat.data.MessageStatus
import uz.itschool.docc.navigation.ScreenType
import kotlin.reflect.KFunction1

data class ChatScreenUiHandlers(
    val onSendMessage: (String) -> Unit = {},
    val onResendMessage: (Message) -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(uiHandlers: ChatScreenUiHandlers = ChatScreenUiHandlers(),
               conversation: LiveData<Conversation>,
               isSendingMessage: LiveData<Boolean>, navController: NavController) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val activity = (LocalContext.current as? Activity)

    val snackbarHostState = remember { SnackbarHostState() }
    var inputValue by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val conversationState by conversation.observeAsState()
    val isSendingMessageState by isSendingMessage.observeAsState()

    fun sendMessage() {
        uiHandlers.onSendMessage(inputValue)
        inputValue = ""
        coroutineScope.launch {
            listState.animateScrollToItem(conversationState?.list?.size ?: 0)
        }
    }
    BackHandler{
    activity?.finish()
    }

    Box {
        Column {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.logo_horizontal),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .padding(top = 30.dp, start = 24.dp)
                            .size(100.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.user_icon),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .padding(top = 30.dp, end = 24.dp)
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            Column {
                Text(
                    text = "Salom 👋",
                    fontSize = 35.sp,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 24.dp, top = 20.dp)
                )
                Row {
                    Text(
                        text = "Quyida sog’lig’ingizdagi o’zgarish va alomatlarni kiriting",
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(start = 24.dp, top = 10.dp)
                            .weight(0.6f)
                    )
                    Spacer(modifier = Modifier.weight(0.4f))
                }

            }

            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 100.dp)
                    .border(width = 2.dp, Color(0xFFC2C2C2), shape = RoundedCornerShape(15.dp))
                    .fillMaxHeight(1f)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxHeight(0.85f)) {
                    conversationState?.let {
                        MessageList(
                            messagesList = it.list,
                            listState = listState,
                            onResendMessage = uiHandlers.onResendMessage
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            OutlinedTextField(
                                value = inputValue,
                                onValueChange = { inputValue = it },
                                shape = RoundedCornerShape(
                                    topEnd = 15.dp,
                                    topStart = 15.dp,
                                    bottomEnd = 15.dp,
                                    bottomStart = 15.dp
                                ),
                                placeholder = {
                                    Text(
                                        text = "Xabar yozish...",
                                        color = Color(0xFF909090),
                                        style = MaterialTheme.typography.bodySmall,
                                        fontSize = 15.sp
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next,
                                    keyboardType = KeyboardType.Text
                                ),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFF8D7BFD),
                                    unfocusedBorderColor = Color(0xFF909090)
                                ),
                                singleLine = false,
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .height(50.dp),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        keyboardController?.hide()
                                    }
                                )
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Box() {
                                Card(
                                    shape = RoundedCornerShape(50),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF8D7BFD)
                                    ),
                                    modifier = Modifier
                                        .height(40.dp)
                                        .width(40.dp)
                                        .align(Alignment.Center)
                                ) {}
                                Icon(
                                    painter = painterResource(id = R.drawable.send_icon),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .align(Alignment.Center)
                                        .fillMaxHeight()
                                        .clickable {
                                                sendMessage()
                                        },
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 30.dp)) {
            ButtonNavigate(to = navController)
        }
    }
}

@Composable
fun ButtonNavigate(to: NavController) {
    Row() {
        Box {
        Card(
            shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF8D7BFD)
            ),
            elevation = CardDefaults.cardElevation(5.dp),
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
                .align(Alignment.Center)
        ) {}
            Row(modifier = Modifier.align(Alignment.Center)) {
                Icon(
                    painter = painterResource(id = R.drawable.chat_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically),
                    tint = Color.White
                )
                Text(
                    text = "Chat",
                    color = Color.White,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
        Box {
        Card(
            shape = RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFFFFF)
            ),
            elevation = CardDefaults.cardElevation(5.dp),
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
                .align(Alignment.Center).clickable {
                    to.navigate(ScreenType.TahlilScreen.route)

                }
        ) {}
            Row(
                Modifier
                    .align(Alignment.Center), Arrangement.Center) {
                Icon(
                    painter = painterResource(id = R.drawable.scan_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                        .fillMaxHeight(),
                    tint = Color.Black
                )
                Text(
                    text = "Tahlil",
                    color = Color.Black,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}
@Composable
fun MessageList(
    messagesList: List<Message>,
    listState: LazyListState,
    onResendMessage: (Message) -> Unit
) {
    LazyColumn(
        state = listState,
        modifier = Modifier.padding(10.dp)
    ) {
        items(messagesList.size) { message ->
            Row {
                if (messagesList[message].isFromUser) {
                    Spacer(Modifier.width(16.dp))
                    Box(
                        modifier = Modifier.weight(weight = 1f)
                    )
                }
                Text(
                    text = messagesList[message].text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (messagesList[message].isFromUser) {
                        Color.White
                    } else {
                        Color.Black
                    },
                    textAlign = if (messagesList[message].isFromUser) { TextAlign.End } else { TextAlign.Start },
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (messagesList[message].messageStatus == MessageStatus.Error) {
                                MaterialTheme.colorScheme.errorContainer
                            } else {
                                if (messagesList[message].isFromUser) {
                                    Color(0xFF8D7BFD)
                                } else {
                                    Color(0xFFC2C2C2)
                                }
                            }
                        )
                        .clickable(enabled = messagesList[message].messageStatus == MessageStatus.Error) {
                            onResendMessage(messagesList[message])
                        }
                        .padding(all = 8.dp)

                )
                if (!messagesList[message].isFromUser) {
                    Spacer(Modifier.width(16.dp))
                    Box(
                        modifier = Modifier.weight(weight = 1f)
                    )
                }
            }
            if (messagesList[message].messageStatus == MessageStatus.Sending) {
                Text(
                    text = "Yuklanmoqda...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.width(32.dp))
            }
            if (messagesList[message].messageStatus == MessageStatus.Error) {
                Row(
                    modifier = Modifier
                        .clickable {
                            onResendMessage(messagesList[message])
                        }
                ) {
                    Box(
                        modifier = Modifier.weight(weight = 1f)
                    )
                    Text(
                        text = "Oops, xatolik",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}