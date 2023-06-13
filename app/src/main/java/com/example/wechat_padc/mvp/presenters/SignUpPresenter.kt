package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.mvp.view.SignUpView

interface SignUpPresenter:IBasePresenter {
    fun initView(view: SignUpView)
    fun onTapVerify()
    fun onTapSignInViaEmail()
    fun onTapGetOTP()
}