package com.enesorhan.gptChat.di

import android.util.Log
import com.enesorhan.gptChat.model.GptImageRequestModel
import com.enesorhan.gptChat.model.GptImageResponseModel
import com.enesorhan.gptChat.model.GptRequestModel
import com.enesorhan.gptChat.model.GptResponseModel
import retrofit2.Response
import javax.inject.Inject

class ChatGptRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun createMessage(apiKey: String, requestBody: GptRequestModel) : Response<GptResponseModel> {
        Log.e("Request","$requestBody")
        return apiServices.createMessage(apiKey, requestBody)
    }

    suspend fun createImage(apiKey: String, requestBody: GptImageRequestModel) : Response<GptImageResponseModel> {
        Log.e("Request","$requestBody")
        return apiServices.createImage(apiKey, requestBody)
    }

}