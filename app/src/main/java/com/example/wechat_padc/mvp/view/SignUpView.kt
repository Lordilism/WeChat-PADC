package com.example.wechat_padc.mvp.view

interface SignUpView: BaseView {
    fun navigateToCreateProfileWithEmail(emailForm: String, phoneNo: String)
}