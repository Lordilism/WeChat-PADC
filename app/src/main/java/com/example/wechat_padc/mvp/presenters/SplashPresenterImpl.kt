package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.mvp.view.SplashView

class SplashPresenterImpl:ViewModel(),SplashPresenter {
    //view
    var mView: SplashView? = null
    override fun initView(view: SplashView) {
        mView = view
    }

    override fun onTapSignUp() {
        mView?.navigateToSignUpScreen()

    }

    override fun onTapLogIn() {
        mView?.navigateToLogInScreen()

    }

    override fun onUiReady(owner: LifecycleOwner) {
        mView?.showSplashScreen()



    }
}