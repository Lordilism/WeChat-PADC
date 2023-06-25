package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.delegates.ContactsDelegate
import com.example.wechat_padc.delegates.GroupDelegate
import com.example.wechat_padc.mvp.view.ChatFragmentView

interface ChatFragmentPresenter:IBasePresenter,ContactsDelegate,GroupDelegate {

    fun initView(view: ChatFragmentView)



}