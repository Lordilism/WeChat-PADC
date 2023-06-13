package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.mvp.view.QrDialogView

class QrDialogPresenterImpl:QrDialogPresenter,ViewModel() {
    private var mView: QrDialogView? = null
    override fun initView(view: QrDialogView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

}