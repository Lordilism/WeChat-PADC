package com.example.wechat_padc.network

import com.example.wechat_padc.data.VO.UserVO

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

    fun getUserData(userUID:String, onSuccess:(UserVO)-> Unit, onFailure:(String)->Unit )
}