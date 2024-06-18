package com.example.daily_project_1_chat_project.ui.viewModel.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daily_project_1_chat_project.domain.SignInWithGoogleUseCase
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val googleSignInClient: GoogleSignInClient
) : ViewModel() {

    val userLiveData = MutableLiveData<FirebaseUser?>()
    val errorLiveData = MutableLiveData<String>()

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            try {
                val user = signInWithGoogleUseCase(idToken)
                userLiveData.postValue(user)
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }

    fun getGoogleSignInIntent() = googleSignInClient.signInIntent
}
