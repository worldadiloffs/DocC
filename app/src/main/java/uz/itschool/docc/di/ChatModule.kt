package uz.itschool.docc.di

import org.koin.dsl.module
import uz.itschool.docc.chat.api.OpenAIRepository
import uz.itschool.docc.chat.data.ConversationRepository
import uz.itschool.docc.chat.domain.ResendMessageUseCase
import uz.itschool.docc.chat.domain.SendChatRequestUseCase
import uz.itschool.docc.screen.main.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import uz.itschool.docc.chat.domain.ObserveMessagesUseCase

val chatModule = module {
    viewModel {
        ChatViewModel(get(), get(), get())
    }
    single { OpenAIRepository(openAI = get()) }
    single { ConversationRepository() }

    single { SendChatRequestUseCase(openAIRepository = get(), conversationRepository = get()) }
    single { ResendMessageUseCase(openAIRepository = get(), conversationRepository = get()) }
    single { ObserveMessagesUseCase(conversationRepository = get()) }
}