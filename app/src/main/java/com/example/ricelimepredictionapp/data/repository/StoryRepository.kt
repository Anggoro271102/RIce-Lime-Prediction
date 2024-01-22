package com.example.ricelimepredictionapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.ricelimepredictionapp.data.database.StoryRoomDatabase
import com.example.ricelimepredictionapp.data.datastore.UserPreferenceDatastore
import com.example.ricelimepredictionapp.data.network.ApiService
import com.example.ricelimepredictionapp.data.paging.AppDataSource
import com.example.ricelimepredictionapp.data.paging.RemoteDataSource
import com.example.ricelimepredictionapp.data.paging.StoryPagingSource
import com.example.ricelimepredictionapp.model.AddStoryResponse
import com.example.ricelimepredictionapp.model.ListStoryItem
import com.example.ricelimepredictionapp.model.LoginResponse
import com.example.ricelimepredictionapp.model.LoginResult
import com.example.ricelimepredictionapp.model.RegisterResponse
import com.example.ricelimepredictionapp.model.ResponseLogin
import com.example.ricelimepredictionapp.model.ResponseRegister
import com.example.ricelimepredictionapp.model.StoryResponse
import java.io.File

class StoryRepository(
    private val apiService: ApiService,
    private val pref: UserPreferenceDatastore,
    private val remoteDataSource: RemoteDataSource,
    private val storyRoomDatabase: StoryRoomDatabase
) : AppDataSource {

    override  fun getUser(): LiveData<LoginResult> {
        return pref.getUser().asLiveData()
    }

    suspend fun saveUser(userName: String, userId: String, userToken: String) {
        pref.saveUser(userName,userId,userToken)
    }

    suspend fun signout() {
        pref.signout()
    }


    override  fun signin(email: String, password: String): LiveData<ResponseLogin> {
        val signinResponse2 = MutableLiveData<ResponseLogin>()

        remoteDataSource.signin(object : RemoteDataSource.SigninCallback{
            override fun onSignin(signinResponse: ResponseLogin) {
                signinResponse2.postValue(signinResponse)
            }
        }, email, password)
        return signinResponse2
    }

    override  fun signup(name: String, email: String, password: String): LiveData<ResponseRegister> {
        val registerResponse = MutableLiveData<ResponseRegister>()

        remoteDataSource.signup(object : RemoteDataSource.SignupCallback{
            override fun onSignup(signupResponse: ResponseRegister) {
                registerResponse.postValue(signupResponse)
            }
        }, name, email, password)
        return registerResponse
    }

    override  fun getAllStory(token: String): LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
            ),
            pagingSourceFactory = {
                StoryPagingSource(
                    apiServicePaging = apiService,
                    dataStoreRepository = pref
                )
            }
        ).liveData
    }

    override  fun postNewStory(token: String, imageFile: File, desc: String, lon: String?, lat: String?): LiveData<AddStoryResponse> {
        val uploadResponseStatus = MutableLiveData<AddStoryResponse>()

        remoteDataSource.postNewStory(object : RemoteDataSource.AddNewStoryCallback{
            override fun onAddStory(addStoryResponse: AddStoryResponse) {
                uploadResponseStatus.postValue(addStoryResponse)
            }
        }, token, imageFile, desc, lon, lat)
        return uploadResponseStatus
    }

    override fun getListMapsStory(token: String): LiveData<StoryResponse> {
        val storyResponse2 = MutableLiveData<StoryResponse>()
        remoteDataSource.getListMapsStory(object: RemoteDataSource.GetListMapsStoryCallback{
            override fun onMapsStoryLoad(storyResponse: StoryResponse) {
                storyResponse2.postValue(storyResponse)
            }

        },token)
        return storyResponse2
    }


    companion object {
        @Volatile
        private var instance: StoryRepository? = null
        fun getInstance(
            apiService: ApiService,
            pref: UserPreferenceDatastore,
            remoteDataSource: RemoteDataSource,
            storyRoomDatabase: StoryRoomDatabase
        ): StoryRepository =
            instance ?: synchronized(this) {
                instance ?: StoryRepository(apiService, pref, remoteDataSource, storyRoomDatabase)
            }.also { instance = it }
    }


}