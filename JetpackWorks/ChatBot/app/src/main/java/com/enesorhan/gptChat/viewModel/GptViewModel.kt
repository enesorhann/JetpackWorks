package com.enesorhan.gptChat.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesorhan.gptChat.Constants.Companion.API_KEY
import com.enesorhan.gptChat.model.GptImageRequestModel
import com.enesorhan.gptChat.model.GptRequestModel
import com.enesorhan.gptChat.model.Messages
import com.enesorhan.gptChat.useCase.ChatGptImageUseCase
import com.enesorhan.gptChat.useCase.ChatGptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GptViewModel @Inject constructor(
    private val useCase: ChatGptUseCase,
    private val imageUseCase: ChatGptImageUseCase
) : ViewModel() {

    private val _generatedMessage = mutableStateOf<List<String>>(emptyList())
    val generatedMessage : State<List<String>> = _generatedMessage


    fun createMessage(text:String){

        addMessage("Enes: $text")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = useCase.invoke(
                apiKey = "Bearer $API_KEY",
                requestBody =  GptRequestModel(
                    model = "gpt-4o-mini",
                    messages = listOf(
                        Messages(
                            role = "system",
                            content = "You are a helpful assistant"
                        ),
                        Messages(
                            role = "user",
                            content = text
                        )
                    ),
                )
            )

                if (response.isSuccessful){
                    response.body()?.let {
                        addMessage(
                            ("Chatbot: " + it.choices.first().message.content)
                        )
                        Log.e("Response -> ", "${response.code()} - ${response.body()} -${response.errorBody()?.string()}")

                    }
                }else{
                    Log.e("API ERROR -> ", "${response.code()} - ${response.errorBody()?.string()}")
                }
            }catch (e:Exception){
                Log.e("Hata Olustu", "${e.localizedMessage} - ${e.message}")
            }

        }

    }

    fun createImage(text:String){

        addMessage("Enes: $text")

        viewModelScope.launch {
            try {
                val response = imageUseCase.invoke(
                    apiKey = "Bearer $API_KEY",
                    requestBody = GptImageRequestModel(
                        model = "dall-e-3",
                        prompt = text,
                        n = 1,
                        size = "1024x1024"
                    )
                )

                if (response.isSuccessful){
                    response.body()?.let {
                        addMessage(
                            (it.data.first().url)
                        )
                        Log.e("Image -> ", " ${response.body()!!.data.first().url}")

                        Log.e("Response -> ", "${response.code()} - ${response.body()} -${response.errorBody()?.string()}")

                    }
                }else{
                    Log.e("API ERROR -> ", "${response.code()} - ${response.errorBody()?.string()}")
                }
            }catch (e:Exception){
                Log.e("Hata Olustu", "${e.localizedMessage} - ${e.message}")
            }

        }

    }

    private fun addMessage(text: String) {
        _generatedMessage.value += text
    }
}