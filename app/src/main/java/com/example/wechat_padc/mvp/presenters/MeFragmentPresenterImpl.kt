package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.data.Models.AuthenticationModel
import com.example.wechat_padc.data.Models.AuthenticationModelImpl
import com.example.wechat_padc.data.Models.UserModel
import com.example.wechat_padc.data.Models.UserModelImpl
import com.example.wechat_padc.mvp.view.MeFragmentView
import com.example.wechat_padc.network.FireStoreApi
import com.example.wechat_padc.network.FireStoreDatabaseImpl


class MeFragmentPresenterImpl:MeFragmentPresenter, ViewModel(){

    private val mAuthenticationModel:AuthenticationModel = AuthenticationModelImpl
    private val mFireStoreApi: FireStoreApi= FireStoreDatabaseImpl
    private val mUserModel: UserModel = UserModelImpl
    private var mView: MeFragmentView? = null
    override fun initView(view: MeFragmentView) {
        mView = view
    }

    override fun onTapQr() {
        mView?.showQrDialog()
    }

    override fun onTapImage() {
        mView?.uploadImage()
    }

    override fun onTapEdit() {
        mView?.showEditDialog()
    }

    override fun onUiReady(owner: LifecycleOwner) {

        val userUID = mAuthenticationModel.getUserUID()

        mFireStoreApi.getUserData(userUID, onSuccess = { userVO ->

            mView?.showUserData(userVO)
            mUserModel.generateQRcode(userUID,
            onSuccess = {
                it
                mView?.bindQrtoView(it)
            },
            onFailure = {
                mView?.showError(it)
            })


        }, onFailure = {
            mView?.showError(it)
        }
        )







    }
}