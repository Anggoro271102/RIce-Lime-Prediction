package com.example.ricelimepredictionapp.model

import com.google.gson.annotations.SerializedName

data class ResponseGetData(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("dateTime")
	val dateTime: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("img_after")
	val imgAfter: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("img_before")
	val imgBefore: String? = null
)
