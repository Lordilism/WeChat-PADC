package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.mvp.view.MeFragmentView


class MeFragmentPresenterImpl:MeFragmentPresenter, ViewModel(){
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

    }
}