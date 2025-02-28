package com.enesorhan.gptChat.model

import com.google.gson.annotations.SerializedName

data class GptResponseModel(
    val id:String,
    @SerializedName("object")
    val objectType:String,
    val created:Long,
    val model:String,
    val choices:List<Choices>,
    val usage: Usage
)

data class Choices(
    val index:Int,
    val message: Messages,
    @SerializedName("finish_reason")
    val finishReason:String
)

data class Usage(
    @SerializedName("prompt_tokens")
    val promptToken:Int,
    @SerializedName("completion_tokens")
    val completionToken:Int,
    @SerializedName("total_tokens")
    val totalToken:Int
)
data class GptImageResponseModel(
    val created:Long,
    val data:List<UrlData>
)

data class UrlData(
    val url:String
)