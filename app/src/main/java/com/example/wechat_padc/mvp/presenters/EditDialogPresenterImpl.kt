package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.mvp.view.EditDialogView

class EditDialogPresenterImpl:EditDialogPresenter,ViewModel() {
    private var mView:EditDialogView? = null
    override fun initView(view: EditDialogView) {
        mView = view
    }

    override fun onTapSave() {
        mView?.saveUserInfo()
    }

    override fun onTapCancel() {
        mView?.cancelEdit()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}