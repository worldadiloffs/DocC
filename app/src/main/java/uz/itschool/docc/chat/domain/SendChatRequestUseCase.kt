package uz.itschool.docc.chat.domain

import uz.itschool.docc.chat.api.OpenAIRepository
import uz.itschool.docc.chat.data.ConversationRepository
import uz.itschool.docc.chat.data.Message
import uz.itschool.docc.chat.data.MessageStatus

class SendChatRequestUseCase(
    private val openAIRepository: OpenAIRepository,
    private val conversationRepository: ConversationRepository
) {

    suspend operator fun invoke(
        prompt: String
    ) {
        val message = Message(
            text = prompt,
            isFromUser = true,
            messageStatus = MessageStatus.Sending
        )
        val conversation = conversationRepository.addMessage(message)

        try {
            val reply = openAIRepository.sendChatRequest(conversation)
            conversationRepository.setMessageStatusToSent(message.id)
            conversationRepository.addMessage(reply)
        } catch (exception: Exception) {
            conversationRepository.setMessageStatusToError(message.id)
        }
    }
}