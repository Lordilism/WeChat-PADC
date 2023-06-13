package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.mvp.view.SplashView

interface SplashPresenter:IBasePresenter {
    fun initView(view: SplashView)
    fun onTapSignUp()
    fun onTapLogIn()
}