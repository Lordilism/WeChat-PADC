package com.example.wechat_padc.data.Models

import com.example.wechat_padc.network.AuthManager
import com.example.wechat_padc.network.FirebaseAuthManagerImpl

object AuthenticationModelImpl : AuthenticationModel {
    override var mAuthManager: AuthManager = FirebaseAuthManagerImpl


    override fun signUp(
        email: String,
        password: String,
        userName: String,
        dateOfBirth: String,
        gender: String,
        userProfile: String,
        phoneNumber: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        mAuthManager.signUpWithEmail(
            email,
            password,
            userName,
            dateOfBirth,
            gender,
            userProfile,
            phoneNumber,
            onSuccess,
            onFailure
        )

    }

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.login(email, password, onSuccess, onFailure)
    }

    override fun getUserUID(): String {
        return mAuthManager.getUserUid()
    }
}