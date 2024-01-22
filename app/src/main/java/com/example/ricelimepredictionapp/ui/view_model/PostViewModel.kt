package com.example.ricelimepredictionapp.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ricelimepredictionapp.data.network_post.ApiConfig
import com.example.ricelimepredictionapp.data.response_predict.ResponsePredict
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class PostViewModel : ViewModel() {
    private val apiService = ApiConfig.createApiService()
    var afterImage: String= ""
    // Ekstensi untuk mengonversi File menjadi RequestBody
    private fun File.toRequestBody(mediaType: String) =
        asRequestBody(mediaType.toMediaTypeOrNull())

    fun uploadImage(imageFile: File?, description: String, callback: (ResponsePredict) -> Unit) {
        imageFile?.let { file ->
            val requestFile = file.toRequestBody("image/*")
            val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)

            val descriptionPart = description.toRequestBody()

            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = apiService.uploadImage(imagePart, descriptionPart)
                    callback(response)
                } catch (e: Exception) {
                    // Tangani kesalahan jika ada
                }
            }
        }
    }

}