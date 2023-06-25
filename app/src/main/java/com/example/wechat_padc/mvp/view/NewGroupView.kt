package com.example.wechat_padc.mvp.view

import com.example.wechat_padc.data.VO.ContactsVO

interface NewGroupView:BaseView {

    fun cancelGroupCreation()
    fun createGroup()
    fun deleteAllTextFromField()
    fun showContactsList(listContactsVO: List<ContactsVO>)
    fun showSelectedContactList(selectedContact: ContactsVO)
    fun showUserData(userUID: String)
    fun openGallery()
    fun showGroupLogo(logoUrl: String)

}