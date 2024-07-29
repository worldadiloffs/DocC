package uz.itschool.docc.screen.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.itschool.docc.model.Card
import uz.itschool.docc.R
import uz.itschool.docc.navigation.ScreenType

@Composable
fun TahlilScreen(navController: NavController) {
    val allCards = remember { mutableListOf<Card>() }
    allCards.add(Card("O'pka", R.drawable.lung, "Tuberklyoz", "Pnevmaniya"))
    allCards.add(Card("Buyrak", R.drawable.kidney, "Tosh yeg'ilishi", "Buyrak chirishi"))
    allCards.add(Card("Jigar", R.drawable.liver, "Jigar serozi", "Jigar qurti"))
    allCards.add(Card("Yurak", R.drawable.heart, "Option1", "Option2"))
    allCards.add(Card("Siniqlar", R.drawable.bones, "Option1", "Option2"))

    val activity = (LocalContext.current as? Activity)
    BackHandler{
        activity?.finish()
    }
    Box(modifier = Modifier) {
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
            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Rentgen tahlili",
                    fontSize = 35.sp,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp)
            ) {
                items(allCards.size) {
                    CardView(card = allCards[it], navController = navController)
                }
            }

        }
        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 30.dp)) {
            ButtonNavigateTahlil(to = navController)
        }
    }
}

@Composable
fun CardView(card: Card, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                navController.navigate("more_screen" + "/${card}")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(5.dp, Color.White),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = card.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = card.title,
                fontSize = 25.sp,
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun ButtonNavigateTahlil(to: NavController) {
    Row() {
        Box {
            Card(
                shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFFFFF)
                ),
                elevation = CardDefaults.cardElevation(5.dp),
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .align(Alignment.Center).clickable {
                        to.navigate(ScreenType.ChatScreen.route)
                    }
            ) {}
            Row(modifier = Modifier.align(Alignment.Center)) {
                Icon(
                    painter = painterResource(id = R.drawable.chat_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically),
                    tint = Color.Black
                )
                Text(
                    text = "Chat",
                    color = Color.Black,
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
                    containerColor = Color(0xFF8D7BFD)
                ),
                elevation = CardDefaults.cardElevation(5.dp),
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .align(Alignment.Center)
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
                        .fillMaxHeight()
                        .clickable {

                        },
                    tint = Color.White
                )
                Text(
                    text = "Tahlil",
                    color = Color.White,
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