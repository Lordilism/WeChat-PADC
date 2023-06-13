package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.mvp.view.ContactsView

class ContactsPresenterImpl:ContactsPresenter,ViewModel() {
    private var mView: ContactsView? = null
    override fun intiView(view: ContactsView) {
        mView = view
    }

    override fun onTapCreateGroup() {
        mView?.createGroup()
    }

    override fun onTapClearText() {
        mView?.deleteAllTextFromField()
    }

    override fun onTapNewContact() {
        mView?.addNewContact()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

}