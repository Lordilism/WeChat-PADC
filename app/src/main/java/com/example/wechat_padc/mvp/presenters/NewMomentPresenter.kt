package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.mvp.view.NewMomentView

interface NewMomentPresenter:IBasePresenter {
    fun initView(view: NewMomentView)
    fun onTapCreate()
    fun onTapBtnClose()

}