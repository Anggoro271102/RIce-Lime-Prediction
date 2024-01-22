package com.example.ricelimepredictionapp.model

import com.google.gson.annotations.SerializedName

data class ResponseDataByUser(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("succces")
	val succces: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data(

	@field:SerializedName("is_admin")
	val isAdmin: Int? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("last_login")
	val lastLogin: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("is_verified")
	val isVerified: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
