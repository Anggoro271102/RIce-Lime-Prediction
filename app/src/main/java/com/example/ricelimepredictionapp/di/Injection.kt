package com.example.ricelimepredictionapp.di

import android.content.Context
import com.example.ricelimepredictionapp.data.database.StoryRoomDatabase
import com.example.ricelimepredictionapp.data.datastore.UserPreferenceDatastore
import com.example.ricelimepredictionapp.data.network.ApiConfig
import com.example.ricelimepredictionapp.data.paging.RemoteDataSource
import com.example.ricelimepredictionapp.data.repository.StoryRepository

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val apiService = ApiConfig.getApiService()
        val userPreferenceDatastore = UserPreferenceDatastore.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val storyRoomDatabase = StoryRoomDatabase.getDatabase(context)
        return StoryRepository.getInstance(apiService, userPreferenceDatastore, remoteDataSource, storyRoomDatabase)
    }
}