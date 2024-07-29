package uz.itschool.docc.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uz.itschool.docc.model.Card
import uz.itschool.docc.model.CardArgType
import com.google.gson.Gson
import uz.itschool.docc.chat.data.Conversation
import uz.itschool.docc.screen.SplashScreen
import uz.itschool.docc.screen.main.ChatScreen
import uz.itschool.docc.screen.main.ChatScreenUiHandlers
import uz.itschool.docc.screen.main.ImageChackerUniqueScreen
import uz.itschool.docc.screen.main.TahlilScreen
import uz.itschool.docc.screen.registration.RegisterScreen
import uz.itschool.docc.screen.registration.SignUpScreen

@Composable
fun NavGraph(navController: NavHostController, uiHandlers: ChatScreenUiHandlers = ChatScreenUiHandlers(),
             conversation: LiveData<Conversation>,
             isSendingMessage: LiveData<Boolean>) {
    NavHost(navController = navController, startDestination = ScreenType.SplashScreen.route) {
        composable(ScreenType.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(ScreenType.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(ScreenType.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(ScreenType.ChatScreen.route) {
            ChatScreen(
                uiHandlers = uiHandlers,
                conversation = conversation,
                isSendingMessage = isSendingMessage,
                navController= navController
            )
        }
        composable(ScreenType.TahlilScreen.route) {
            TahlilScreen(navController = navController)
        }

        composable(ScreenType.ImageChackerUniqueScreen.route, arguments = listOf(navArgument("card") {
            type = CardArgType()
        })) {
            val card = it.arguments?.getString("card")
                ?.let { it1 -> Gson().fromJson(it1, Card::class.java) }
            ImageChackerUniqueScreen(navController = navController, card = card!!)
        }
    }
}