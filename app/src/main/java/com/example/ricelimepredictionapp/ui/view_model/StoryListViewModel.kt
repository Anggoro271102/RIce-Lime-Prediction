package com.example.ricelimepredictionapp.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ricelimepredictionapp.data.repository.StoryRepository
import java.io.File

class StoryListViewModel(private val repo: StoryRepository) : ViewModel() {

    val coordLat = MutableLiveData(0.0)
    val coordLon = MutableLiveData(0.0)

    fun getAllStory(token: String) =  repo.getAllStory(token)
    fun postNewStory(token: String, imageFile: File, desc: String, lon: String?, lat: String?) = repo.postNewStory(token, imageFile, desc, lon, lat)
    fun getListMapsStory(token: String) = repo.getListMapsStory(token)
}