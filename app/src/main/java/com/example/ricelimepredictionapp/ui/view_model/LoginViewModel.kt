package com.example.ricelimepredictionapp.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ricelimepredictionapp.data.repository.StoryRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: StoryRepository) : ViewModel() {

    fun signin(email: String, password: String) = repo.signin(email, password)

    fun getUser() = repo.getUser()

    fun saveUser(userName: String, userId: String, userToken: String) {
        viewModelScope.launch {
            repo.saveUser(userName,userId,userToken)
        }
    }

    fun signout() {
        viewModelScope.launch {
            repo.signout()
        }
    }

}