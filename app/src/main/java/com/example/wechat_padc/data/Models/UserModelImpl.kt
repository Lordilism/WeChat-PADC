package com.example.wechat_padc.data.Models

import com.example.wechat_padc.network.FireStoreApi
import com.example.wechat_padc.network.FireStoreDatabaseImpl

object UserModelImpl : UserModel {
    override var mFirestoreApi: FireStoreApi = FireStoreDatabaseImpl


    override fun addUser(
        email: String,
        password: String,
        userName: String,
        dateOfBirth: String,
        gender: String,
        userUID: String,
        profile: String
    ) {
        mFirestoreApi.addUser(email =email,password, userName, dateOfBirth, gender, userUID, profile)
    }
}