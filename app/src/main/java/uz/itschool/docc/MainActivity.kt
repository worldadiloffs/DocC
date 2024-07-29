package uz.itschool.docc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import androidx.navigation.compose.rememberNavController
import uz.itschool.docc.navigation.NavGraph
import uz.itschool.docc.screen.main.ChatScreenUiHandlers
import uz.itschool.docc.screen.main.ChatViewModel
import uz.itschool.docc.ui.theme.DocCTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ChatViewModel by stateViewModel(
        state = { intent?.extras ?: Bundle() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DocCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(
                        navController = rememberNavController(), uiHandlers = ChatScreenUiHandlers(
                            onSendMessage = viewModel::sendMessage,
                            onResendMessage = viewModel::resendMessage
                        ),
                        conversation = viewModel.conversation,
                        isSendingMessage = viewModel.isSendingMessage
                    )
                }
            }
        }
    }
}