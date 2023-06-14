package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.data.Models.AuthenticationModel
import com.example.wechat_padc.data.Models.AuthenticationModelImpl
import com.example.wechat_padc.mvp.view.LogInView

class LogInPresenterImpl : ViewModel(), LogInPresenter {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private var mView: LogInView? = null
    override fun initView(view: LogInView) {
        mView = view

    }

    override fun onTapLogIn(email: String, password: String) {
        mAuthenticationModel.login(email, password, onSuccess = {
            mView?.navigateToMainScreen()

        }, onFailure = {
            mView?.showError(it)
        })

    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}