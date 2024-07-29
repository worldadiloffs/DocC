package uz.itschool.docc.chat.domain

import uz.itschool.docc.chat.data.ConversationRepository

class ObserveMessagesUseCase(
    private val conversationRepository: ConversationRepository
) {

    operator fun invoke() = conversationRepository.conversationFlow

}