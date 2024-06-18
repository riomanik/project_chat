package com.example.daily_project_1_chat_project.data.repository

import androidx.lifecycle.liveData
import com.example.daily_project_1_chat_project.data.model.ChatMessage
import com.example.daily_project_1_chat_project.data.model.ChatRequest
import com.example.daily_project_1_chat_project.data.model.ChatResponse
import com.example.daily_project_1_chat_project.data.network.RetrofitInstance
import com.example.daily_project_1_chat_project.util.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class ChatRepository {

    fun getChatResponse(message: String) = liveData<Resource<ChatResponse>>(Dispatchers.IO) {
        emit(Resource.Loading(null))
        try {
            val chatMessage = ChatMessage(role = "user", content = message)
            val request = ChatRequest(messages = listOf(chatMessage))
            val response = getChatCompletion(request)
            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error("Error: ${response.message()}", null))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Exception: ${e.message}", null))
        }
    }

    private fun getChatCompletion(request: ChatRequest): Response<ChatResponse> {
        return RetrofitInstance.api.getChatCompletion(request).execute()
    }
}


