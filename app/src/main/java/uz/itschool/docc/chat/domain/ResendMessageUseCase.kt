package uz.itschool.docc.chat.domain

import uz.itschool.docc.chat.api.OpenAIRepository
import uz.itschool.docc.chat.data.ConversationRepository
import uz.itschool.docc.chat.data.Message

class ResendMessageUseCase(
    private val openAIRepository: OpenAIRepository,
    private val conversationRepository: ConversationRepository
) {

    suspend operator fun invoke(
        message: Message
    ) {
        val conversation = conversationRepository.resendMessage(message)

        try {
            val reply = openAIRepository.sendChatRequest(conversation)
            conversationRepository.setMessageStatusToSent(message.id)
            conversationRepository.addMessage(reply)
        } catch (exception: Exception) {
            conversationRepository.setMessageStatusToError(message.id)
        }
    }
}