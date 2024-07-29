package uz.itschool.docc.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import uz.itschool.docc.R
import uz.itschool.docc.navigation.ScreenType

@Composable
fun SplashScreen(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(tween(3000)),
        exit = fadeOut(tween(3000)),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_splash),
                contentDescription = null,
                modifier = Modifier,
                Alignment.Center,
                contentScale = ContentScale.Fit
            )
        }
    }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            delay(3000L) // however long you want the animation to run
            isVisible = false
        }
        navController.navigate(ScreenType.RegisterScreen.route)
//        if (sharedPreferences.getUser().isEmpty()){
//            navController.navigate(ScreenType.SignInScreen.route)
//        }else{
//            navController.navigate(ScreenType.MainScreen.route)
//        }
    }

}