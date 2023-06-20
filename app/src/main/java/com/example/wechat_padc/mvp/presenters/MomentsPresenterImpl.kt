package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.data.Models.AuthenticationModel
import com.example.wechat_padc.data.Models.AuthenticationModelImpl
import com.example.wechat_padc.data.Models.UserModel
import com.example.wechat_padc.data.Models.UserModelImpl
import com.example.wechat_padc.data.VO.UserVO
import com.example.wechat_padc.mvp.view.MomentsView
import com.example.wechat_padc.network.FireStoreApi
import com.example.wechat_padc.network.FireStoreDatabaseImpl

class MomentsPresenterImpl : ViewModel(), MomentPresenter {
    private var mView: MomentsView? = null
    private val mAuthentication: AuthenticationModel = AuthenticationModelImpl
    private val mFireStoreApi: FireStoreApi = FireStoreDatabaseImpl
    private val mUserModel:UserModel = UserModelImpl
    private lateinit var mUserVO: UserVO

    override fun initView(view: MomentsView) {
        mView = view
    }

    override fun onTapCreateMoments() {
        mView?.navigateToCreateMoment()
    }


    override fun onUiReady(owner: LifecycleOwner) {
        val userUID = mAuthentication.getUserUID()

        mFireStoreApi.getUserData(userUID, onSuccess = {
            mUserVO = it
        }, onFailure = {
            mView?.showError(it)
        })

        mUserModel.getFeed(
            onSuccess = {
                mView?.showFeed(it)
            },
            onFailure = {
                mView?.showError(it)
            }
        )


    }
}