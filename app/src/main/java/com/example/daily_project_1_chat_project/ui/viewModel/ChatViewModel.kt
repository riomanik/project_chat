//package com.example.daily_project_1_chat_project.ui.viewModel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.daily_project_1_chat_project.data.model.ChatResponse
//import com.example.daily_project_1_chat_project.data.repository.ChatRepository
//import com.example.daily_project_1_chat_project.util.Resource
//import kotlinx.coroutines.launch
//
//class ChatViewModel(private val repository: ChatRepository) : ViewModel() {
//
//    private val _chatResponse = MutableLiveData<Resource<ChatResponse>>()
//    val chatResponse: LiveData<Resource<ChatResponse>> get() = _chatResponse
//
//    fun fetchChatResponse(message: String) {
//        _chatResponse.postValue(Resource.Loading(null))
//        viewModelScope.launch {
//            try {
//                val response = repository.getChatResponse(message)
//                response.observeForever {
//                    _chatResponse.postValue(it)
//                }
//            } catch (e: Exception) {
//                _chatResponse.postValue(Resource.Error("Exception: ${e.message}", null))
//            }
//        }
//    }
//}
//
//
