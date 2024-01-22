package com.example.ricelimepredictionapp.data.network

import com.example.ricelimepredictionapp.model.AddStoryResponse
import com.example.ricelimepredictionapp.model.LoginResponse
import com.example.ricelimepredictionapp.model.RegisterResponse
import com.example.ricelimepredictionapp.model.ResponseLogin
import com.example.ricelimepredictionapp.model.ResponseRegister
import com.example.ricelimepredictionapp.model.StoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.QueryMap

interface ApiService {
    @GET("/v1/stories")
    suspend fun ListStory(
        @Header("Authorization") bearer: String?,
        @QueryMap queries: Map<String, Int>,
    ): StoryResponse

    @Multipart
    @POST("/v1/stories")
    fun postNewStory(
        @Header("Authorization") bearer: String?,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody?,
        @Part("lat") lat: RequestBody?,
        @Part("lon") lon: RequestBody?
    ): Call<AddStoryResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<ResponseRegister>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<ResponseLogin>

    @GET("/v1/stories?location=1")
    fun getListMapsStory(
        @Header("Authorization") bearer: String?
    ): Call<StoryResponse>
}