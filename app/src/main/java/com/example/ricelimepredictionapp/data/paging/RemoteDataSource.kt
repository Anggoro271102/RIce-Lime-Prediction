package com.example.ricelimepredictionapp.data.paging

import androidx.lifecycle.MutableLiveData
import com.example.ricelimepredictionapp.data.network.ApiConfig
import com.example.ricelimepredictionapp.model.AddStoryResponse
import com.example.ricelimepredictionapp.model.LoginResponse
import com.example.ricelimepredictionapp.model.RegisterResponse
import com.example.ricelimepredictionapp.model.ResponseLogin
import com.example.ricelimepredictionapp.model.ResponseRegister
import com.example.ricelimepredictionapp.model.StoryResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class RemoteDataSource {
    val error = MutableLiveData("")
    var responsecode = ""

    fun signin(callback: SigninCallback, email: String, password: String) {
        callback.onSignin(
            ResponseLogin(
                "",
                null,
                ""
            )
        )

        val client = ApiConfig.getApiService().login(email, password)
        client.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {

                if(response.isSuccessful){
                    response.body()?.let { callback.onSignin(it) }
                }else {
                    when (response.code()) {
                        200 -> responsecode = "200"
                        400 -> responsecode = "400"
                        401 -> responsecode = "401"
                        else -> error.postValue("ERROR ${response.code()} : ${response.message()}")
                    }
                    callback.onSignin(
                        ResponseLogin(
                            "null",
                            null,
                            ""

                        )
                    )
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                callback.onSignin(
                    ResponseLogin(
                        "",
                        null,
                        ""
                    )
                )
            }
        })
    }

    fun signup(callback: SignupCallback, name: String, email: String, password: String){
        val signupinfo = ResponseRegister(
            "",
        )
        callback.onSignup(
            signupinfo
        )
        val client = ApiConfig.getApiService().register(name, email, password)
        client.enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                if(response.isSuccessful){
                    response.body()?.let { callback.onSignup(it) }
                    responsecode = "201"
                    callback.onSignup(
                        ResponseRegister(
                            ""
                        )
                    )
                }else {
                    responsecode = "400"
                    callback.onSignup(
                        ResponseRegister(
                            ""
                        )
                    )
                }
            }
            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                callback.onSignup(
                    ResponseRegister(
                        "",
                    )
                )
            }
        })
    }

    fun postNewStory(callback: AddNewStoryCallback, token: String, imageFile: File, desc: String, lon: String? = null, lat: String? = null){
        callback.onAddStory(
            addStoryResponse = AddStoryResponse(
                true,
                ""
            )
        )

        val description = desc.toRequestBody("text/plain".toMediaType())
        val latitude = lat?.toRequestBody("text/plain".toMediaType())
        val logitude = lon?.toRequestBody("text/plain".toMediaType())
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo",
            imageFile.name,
            requestImageFile
        )
        val client = ApiConfig.getApiService().postNewStory(bearer = "Bearer $token", imageMultipart, description, latitude!!, logitude!!)

        client.enqueue(object : Callback<AddStoryResponse> {
            override fun onResponse(
                call: Call<AddStoryResponse>,
                response: Response<AddStoryResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error){
                        callback.onAddStory(responseBody)
                        callback.onAddStory(
                            addStoryResponse = AddStoryResponse(
                                true,
                                "$latitude!!, $logitude!!"
                            )
                        )
                    }else{
                        callback.onAddStory(
                            addStoryResponse = AddStoryResponse(
                                true,
                                "Gagal upload file"
                            )
                        )
                    }
                }
                else{
                    callback.onAddStory(
                        addStoryResponse = AddStoryResponse(
                            true,
                            "Gagal upload file"
                        )
                    )
                }
            }

            override fun onFailure(call: Call<AddStoryResponse>, t: Throwable) {
                callback.onAddStory(
                    addStoryResponse = AddStoryResponse(
                        true,
                        "Gagal upload file"
                    )
                )
            }
        })
    }

    fun getListMapsStory(callback: GetListMapsStoryCallback, token: String){
        val client = ApiConfig.getApiService().getListMapsStory(bearer = "Bearer $token")
        client.enqueue(object : Callback<StoryResponse> {
            override fun onResponse(
                call: Call<StoryResponse>,
                response: Response<StoryResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { callback.onMapsStoryLoad(it) }
                }else{
                    val storyResponse = StoryResponse(
                        emptyList(),
                        true,
                        "Load Failed!"
                    )
                    callback.onMapsStoryLoad(storyResponse)
                }
            }

            override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                val storyResponse = StoryResponse(
                    emptyList(),
                    true,
                    t.message.toString()
                )
                callback.onMapsStoryLoad(storyResponse)
            }
        })
    }


    interface SigninCallback{
        fun onSignin(loginResponse: ResponseLogin)
    }

    interface SignupCallback{
        fun onSignup(signupResponse: ResponseRegister)
    }

    interface GetListMapsStoryCallback{
        fun onMapsStoryLoad(storyResponse: StoryResponse)
    }

    interface AddNewStoryCallback{
        fun onAddStory(addStoryResponse: AddStoryResponse)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}