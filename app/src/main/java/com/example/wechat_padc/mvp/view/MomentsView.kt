package com.example.wechat_padc.mvp.view

import com.example.wechat_padc.data.VO.MomentsVO

interface MomentsView:BaseView {
    fun navigateToCreateMoment()
    fun showFeed(list: List<MomentsVO>)

}