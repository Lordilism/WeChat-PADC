package com.example.wechat_padc.mvp.view

import com.example.wechat_padc.data.VO.ContactsVO

interface ChatFragmentView:BaseView {
    fun showActiveUser(listContactVO: List<ContactsVO>)
    fun showLatestMessage(messageList: List<String>,signInUserUID:String)
    fun showJoindGroups(joinedGroup: MutableSet<Long>)
    fun navigateToChat(msgReceiverUid: String, userUID: String)
    fun navigateToChatGroup(groupID: String, mCurrentUserUID: String)

}