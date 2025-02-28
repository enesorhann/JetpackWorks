package com.enesorhan.gptChat.model

data class GptRequestModel(
    val model:String,
    val messages:List<Messages>,
)

data class Messages(
    val role:String,
    val content:String
)

data class GptImageRequestModel(
    val model: String,
    val prompt: String,
    val n: Int,
    val size: String,
)