package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.mvp.view.MomentsView

class MomentsPresenterImpl: ViewModel(),MomentPresenter {
    private var mView: MomentsView? = null
    override fun initView(view: MomentsView) {
        mView = view
    }

    override fun onTapCreateMoments() {
        mView?.navigateToCreateMoment()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}