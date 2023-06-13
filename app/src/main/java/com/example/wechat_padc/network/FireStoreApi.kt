package com.example.wechat_padc.network

interface FireStoreApi {
    fun addUser(
        email:String,
        password: String,
        userName: String,
        dateOfBirth: String,
        gender: String,
        userUID: String,
        profile: String
    )
}