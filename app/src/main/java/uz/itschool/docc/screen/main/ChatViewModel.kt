package uz.itschool.docc.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.itschool.docc.chat.data.Conversation
import uz.itschool.docc.chat.data.Message
import uz.itschool.docc.chat.data.MessageStatus
import uz.itschool.docc.chat.domain.ObserveMessagesUseCase
import uz.itschool.docc.chat.domain.ResendMessageUseCase
import uz.itschool.docc.chat.domain.SendChatRequestUseCase

class ChatViewModel(
    private val sendChatRequestUseCase: SendChatRequestUseCase,
    private val resendChatRequestUseCase: ResendMessageUseCase,
    private val observeMessagesUseCase: ObserveMessagesUseCase,
) : ViewModel() {

    private val _conversation = MutableLiveData<Conversation>()
    val conversation: LiveData<Conversation> = _conversation

    private val _isSendingMessage = MutableLiveData<Boolean>()
    val isSendingMessage: LiveData<Boolean> = _isSendingMessage

    init {
        observeMessageList()
    }

    private fun observeMessageList() {
        viewModelScope.launch {
            observeMessagesUseCase.invoke().collect { conversation ->
                _conversation.postValue(conversation)

                _isSendingMessage.postValue(
                    conversation.list.any { it.messageStatus == MessageStatus.Sending }
                )
            }
        }
    }

    fun sendMessage(prompt: String) {
        viewModelScope.launch {
            sendChatRequestUseCase(
                prompt
            )
        }
    }

    fun resendMessage(message: Message) {
        viewModelScope.launch {
            resendChatRequestUseCase(
                message
            )
        }
    }
}