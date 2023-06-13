package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.mvp.view.NewGroupView

class NewGroupPresenterImpl:NewGroupPresenter, ViewModel() {
    private var mView: NewGroupView? = null
    override fun initView(view: NewGroupView) {
        mView = view
    }

    override fun onTapClose() {
        mView?.cancelGroupCreation()

    }

    override fun onTapCreate() {
        mView?.createGroup()
    }

    override fun onTapTextDelete() {
       mView?.deleteAllTextFromField()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}