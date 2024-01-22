package com.example.ricelimepredictionapp.data.response_predict

import com.google.gson.annotations.SerializedName

data class ResponsePredict(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: Status? = null
)

data class Data(

	@field:SerializedName("result_image_path")
	val resultImagePath: String? = null
)

data class Status(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)
