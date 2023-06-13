package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.mvp.view.QrDialogView

interface QrDialogPresenter:IBasePresenter {
    fun initView( view: QrDialogView)
}