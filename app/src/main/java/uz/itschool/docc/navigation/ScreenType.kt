package uz.itschool.docc.navigation

sealed class ScreenType(val route: String) {
    data object SplashScreen : ScreenType("splash_screen")
    data object RegisterScreen : ScreenType("register_screen")
    data object SignUpScreen : ScreenType("signup_screen")
    data object ChatScreen : ScreenType("chat_screen")
    data object TahlilScreen : ScreenType("tahlil_screen")
    data object TaScreen : ScreenType("ta_screen")
    data object ImageChackerUniqueScreen : ScreenType("more_screen" + "/{card}")

}