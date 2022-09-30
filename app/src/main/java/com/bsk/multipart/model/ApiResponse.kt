package com.bsk.multipart.model

data class ApiResponse(
    val  data: Data,
    val success: Boolean,
    val message:String
)
data class Data(
    val _id: String,
    val emailId: String,
    val profilePic: String,
    val role: String,
    val userName: String
)