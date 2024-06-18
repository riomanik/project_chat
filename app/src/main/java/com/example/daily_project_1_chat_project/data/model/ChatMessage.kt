package com.example.daily_project_1_chat_project.data.model

data class ChatMessage(
    val role: String,
    val content: String
)

data class ChatRequest(
    val model: String = "gpt-4",
    val messages: List<ChatMessage>
)

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: ChatMessage
)
