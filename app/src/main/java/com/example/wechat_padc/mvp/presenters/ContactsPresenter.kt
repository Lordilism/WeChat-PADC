package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.delegates.ContactsDelegate
import com.example.wechat_padc.delegates.GroupDelegate
import com.example.wechat_padc.mvp.view.ContactsView

interface ContactsPresenter : IBasePresenter,ContactsDelegate,GroupDelegate {
    fun intiView(view: ContactsView)
    fun onTapCreateGroup()
    fun onTapClearText()
    fun onTapNewContact()

    fun addNewContactOnStore(contents: String)


}