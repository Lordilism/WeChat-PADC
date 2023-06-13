package com.example.wechat_padc.data.VO.Models

import com.example.wechat_padc.network.FireStoreApi

interface UserModel {
    var mFirestoreApi: FireStoreApi

    fun addUser(
        email: String,
        password: String,
        userName: String,
        dateOfBirth: String,
        gender: String,
        userUID: String,
        profile: String
    )
}