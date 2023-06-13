package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.mvp.view.MeFragmentView

interface MeFragmentPresenter:IBasePresenter {
    fun initView(view: MeFragmentView)
    fun onTapQr()
    fun onTapImage()
    fun onTapEdit()
}