package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.mvp.view.NewGroupView

interface NewGroupPresenter:IBasePresenter {
    fun initView(view:NewGroupView)
    fun onTapClose()
    fun onTapCreate()
    fun onTapTextDelete()
}