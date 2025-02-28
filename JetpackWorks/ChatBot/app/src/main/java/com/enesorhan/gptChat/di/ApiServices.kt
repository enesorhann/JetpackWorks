package com.enesorhan.gptChat.di

import com.enesorhan.gptChat.model.GptImageRequestModel
import com.enesorhan.gptChat.model.GptImageResponseModel
import com.enesorhan.gptChat.model.GptRequestModel
import com.enesorhan.gptChat.model.GptResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiServices {

    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    suspend fun createMessage(
        @Header("Authorization") apiKey: String,
        @Body requestBody: GptRequestModel
    ): Response<GptResponseModel>

    @Headers("Content-Type: application/json")
    @POST("v1/images/generations")
    suspend fun createImage(
        @Header("Authorization") apiKey: String,
        @Body requestBody: GptImageRequestModel
    ): Response<GptImageResponseModel>

}