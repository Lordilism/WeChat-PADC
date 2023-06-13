package com.example.wechat_padc.network

interface AuthManager {

    fun signUpWithEmail(email:String, password:String, userName:String, dateOfBirth:String, gender:String, onSuccess: () -> Unit, onFailure:(String)->Unit)
}
