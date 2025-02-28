package com.enesorhan.gptChat.useCase

import com.enesorhan.gptChat.di.ChatGptRepository
import com.enesorhan.gptChat.model.GptImageRequestModel
import com.enesorhan.gptChat.model.GptImageResponseModel
import com.enesorhan.gptChat.model.GptRequestModel
import com.enesorhan.gptChat.model.GptResponseModel
import retrofit2.Response
import javax.inject.Inject

class ChatGptUseCase @Inject constructor(private val repository: ChatGptRepository) {

    suspend operator fun invoke(
        apiKey: String,
        requestBody: GptRequestModel
    ): Response<GptResponseModel> = repository.createMessage(apiKey,requestBody)

}