package com.example.ricelimepredictionapp.ui.view_model

import androidx.lifecycle.ViewModel
import com.example.ricelimepredictionapp.data.repository.StoryRepository


class RegisterViewModel(private val repo: StoryRepository) : ViewModel() {

    fun signup(name: String, email: String, password: String) = repo.signup(name, email, password)
}

