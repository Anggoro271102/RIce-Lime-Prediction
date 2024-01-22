package com.example.ricelimepredictionapp.data.network_post

import com.example.ricelimepredictionapp.data.response_predict.ResponsePredict
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("prediction") // Ganti dengan path endpoint yang sesuai
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("description") description: RequestBody // Jika Anda memiliki parameter lain
    ): ResponsePredict // Ganti YourResponseModel dengan model respon yang sesuai
}
