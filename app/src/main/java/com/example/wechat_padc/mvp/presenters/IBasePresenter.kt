package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {
    fun onUiReady(owner:LifecycleOwner)
}