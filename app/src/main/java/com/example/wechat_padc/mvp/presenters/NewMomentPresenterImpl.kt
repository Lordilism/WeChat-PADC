package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.mvp.view.NewMomentView

class NewMomentPresenterImpl:NewMomentPresenter,ViewModel() {
    private var mView:NewMomentView?= null
    override fun initView(view: NewMomentView) {
        mView = view
    }

    override fun onTapCreate() {

    }

    override fun onTapBtnClose() {
        mView?.closeMomentCreate()

    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}