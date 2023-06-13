package com.example.wechat_padc.data.VO.Models

import com.example.wechat_padc.network.AuthManager
import com.example.wechat_padc.network.FirebaseAuthManagerImpl

object AuthenticationModelImpl:AuthenticationModel {
    override var mAuthManager: AuthManager = FirebaseAuthManagerImpl


    override fun signUp(
        email: String,
        password: String,
        userName: String,
        dateOfBirth: String,
        gender: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        mAuthManager.signUpWithEmail(email,password,userName,dateOfBirth,gender,onSuccess,onFailure)

    }
}