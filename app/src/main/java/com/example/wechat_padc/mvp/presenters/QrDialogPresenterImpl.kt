package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.data.Models.AuthenticationModel
import com.example.wechat_padc.data.Models.AuthenticationModelImpl
import com.example.wechat_padc.data.Models.UserModel
import com.example.wechat_padc.data.Models.UserModelImpl
import com.example.wechat_padc.mvp.view.QrDialogView

class QrDialogPresenterImpl:QrDialogPresenter,ViewModel() {
    private var mView: QrDialogView? = null
    private val mUserModel: UserModel = UserModelImpl
    private val mAuthenticationModel:AuthenticationModel = AuthenticationModelImpl
    override fun initView(view: QrDialogView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        val userUID = mAuthenticationModel.getUserUID()
        mUserModel.generateQRcode(userUID, onSuccess = {
            mView?.bindQr(it)
        },
        onFailure = {
            mView?.showError(it)
        })


    }

}