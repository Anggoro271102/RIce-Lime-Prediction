package com.example.ricelimepredictionapp.data.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.ricelimepredictionapp.model.AddStoryResponse
import com.example.ricelimepredictionapp.model.ListStoryItem
import com.example.ricelimepredictionapp.model.LoginResponse
import com.example.ricelimepredictionapp.model.LoginResult
import com.example.ricelimepredictionapp.model.RegisterResponse
import com.example.ricelimepredictionapp.model.ResponseLogin
import com.example.ricelimepredictionapp.model.ResponseRegister
import com.example.ricelimepredictionapp.model.StoryResponse
import java.io.File

interface AppDataSource {
    fun getUser(): LiveData<LoginResult>
    fun signin(email: String, password: String): LiveData<ResponseLogin>
    fun signup(name: String, email: String, password: String): LiveData<ResponseRegister>
    fun postNewStory(token: String, imageFile: File, desc: String, lon: String?, lat: String?): LiveData<AddStoryResponse>
    fun getAllStory(token: String): LiveData<PagingData<ListStoryItem>>
    fun getListMapsStory(token: String): LiveData<StoryResponse>
}