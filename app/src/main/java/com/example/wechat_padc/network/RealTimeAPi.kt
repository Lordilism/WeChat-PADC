package com.example.wechat_padc.network

import com.example.wechat_padc.data.VO.GroupVO
import com.example.wechat_padc.data.VO.MessageVO

interface RealTimeAPi {
    fun sendText(vo: MessageVO, receiverUID:String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getMessage(userUID:String,receiverUID:String,onSuccess:(MutableList<MessageVO>)->Unit,onFailure: (String) -> Unit)
    fun creatGroup(groupLogo:String,groupName: String, members: MutableList<String>,timeStamp:Long,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun getGroup(onSuccess: (List<GroupVO>) -> Unit, onFailure: (String) -> Unit)
    fun sendTextGroup(vo: MessageVO, groupID: String,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun getMessageGroup(
        groupId: String,
        onSuccess: (List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getGroupInfo(groupID: String, onSucces: (GroupVO) -> Unit, onFailure: (String) -> Unit)
    fun getLatestMessage(
        userUID: String,
        onSuccess: (List<String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMessagesOfContacts(
        mCurrentUserUid: String,
        messagedContactID: String,
        onSuccess: (List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}