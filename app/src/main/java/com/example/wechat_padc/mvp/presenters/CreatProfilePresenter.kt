package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.mvp.view.CreatProfileView

interface CreatProfilePresenter:IBasePresenter {
    fun initView(view: CreatProfileView)
    fun onTapSignUp(email:String, name: String, password: String, day: String, month: String, year: String, gender: String)

}