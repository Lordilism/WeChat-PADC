package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.mvp.view.LogInView

class LogInPresenterImpl:ViewModel(),LogInPresenter {
    private var mView: LogInView? = null
    override fun initView(view: LogInView) {
        mView = view

    }

    override fun onTapLogIn() {
        mView?.navigateToMainScreen()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}