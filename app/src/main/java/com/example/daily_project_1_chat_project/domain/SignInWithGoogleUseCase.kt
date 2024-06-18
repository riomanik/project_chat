package com.example.daily_project_1_chat_project.domain

import com.example.daily_project_1_chat_project.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class SignInWithGoogleUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(idToken: String): FirebaseUser? {
        return repository.signInWithGoogle(idToken)
    }
}