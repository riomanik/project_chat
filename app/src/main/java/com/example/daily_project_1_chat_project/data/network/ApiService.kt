package com.example.daily_project_1_chat_project.data.network

import com.example.daily_project_1_chat_project.data.model.ChatRequest
import com.example.daily_project_1_chat_project.data.model.ChatResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    fun getChatCompletion(@Body request: ChatRequest): Call<ChatResponse>

}
