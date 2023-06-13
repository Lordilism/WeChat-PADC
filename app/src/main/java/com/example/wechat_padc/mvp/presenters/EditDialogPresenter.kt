package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.mvp.view.EditDialogView

interface EditDialogPresenter:IBasePresenter {
    fun initView(view: EditDialogView)
    fun onTapSave()
    fun onTapCancel()
}