package com.example.wechat_padc.network

interface AuthManager {

    fun signUpWithEmail(email:String, password:String, userName:String, dateOfBirth:String, gender:String,userProfile:String, onSuccess: () -> Unit, onFailure:(String)->Unit)
    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun getUserUid():String
}
