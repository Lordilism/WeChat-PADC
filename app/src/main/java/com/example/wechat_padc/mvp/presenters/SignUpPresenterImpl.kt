package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.mvp.view.SignUpView

class SignUpPresenterImpl:ViewModel(),SignUpPresenter {
    var mView: SignUpView?= null
    override fun initView(view: SignUpView) {
        mView =view
    }

    override fun onTapVerify() {

    }

    override fun onTapSignInViaEmail() {
        mView?.navigateToCreateProfileWithEmail()
    }

    override fun onTapGetOTP() {

    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}