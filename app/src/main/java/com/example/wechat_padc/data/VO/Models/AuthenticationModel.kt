package com.example.wechat_padc.data.VO.Models

import com.example.wechat_padc.network.AuthManager

interface AuthenticationModel {
    var mAuthManager: AuthManager
    fun signUp(email: String, password: String, userName: String, dateOfBirth: String, gender: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
}