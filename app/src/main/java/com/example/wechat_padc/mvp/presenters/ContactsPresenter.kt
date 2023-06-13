package com.example.wechat_padc.mvp.presenters

import com.example.wechat_padc.mvp.view.ContactsView

interface ContactsPresenter : IBasePresenter {
    fun intiView(view: ContactsView)
    fun onTapCreateGroup()
    fun onTapClearText()
    fun onTapNewContact()

}