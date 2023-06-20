package com.example.wechat_padc.mvp.view

import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.data.VO.GroupVO

interface ContactsView:BaseView {
    fun searhContacts()
    fun createGroup()
    fun deleteAllTextFromField()
    fun openQrScanner()

    fun showSuccessMessage()
    fun showContactsList(contactsList: List<ContactsVO>)
    fun navigateToChatActivity(s:String)
    fun currentUserUID(userUid: String)
    fun showGroup(listVO: MutableList<GroupVO>)
    fun navigateToChatActivityFromGroup(groupID: String)

}