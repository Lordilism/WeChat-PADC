package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.mvp.view.LogInView

interface LogInPresenter:IBasePresenter {
    fun initView(view: LogInView)
    fun onTapLogIn()
}