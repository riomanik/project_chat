package com.example.daily_project_1_chat_project.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.daily_project_1_chat_project.databinding.ActivityLoginBinding
import com.example.daily_project_1_chat_project.ui.viewModel.login.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initLiveDataObserver()
    }

    private fun initLiveDataObserver() {
        viewModel.userLiveData.observe(this, Observer {
            it?.let {
                Toast.makeText(this, it.email, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initView() {
        binding.btnSignGoogle.setOnClickListener{
            signIn()
        }
    }

    private fun initializeViewModel() {
    }

    private fun signIn() {
        val signInIntent = viewModel.getGoogleSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                viewModel.signInWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Handle error
            }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }
}

