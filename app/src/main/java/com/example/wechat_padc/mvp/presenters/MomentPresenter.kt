package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.mvp.view.MomentsView

interface MomentPresenter:IBasePresenter {
    fun initView(view: MomentsView)
    fun onTapCreateMoments()


}