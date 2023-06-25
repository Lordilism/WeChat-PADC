package com.example.wechat_padc.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.Models.UserModel
import com.example.wechat_padc.data.Models.UserModelImpl
import com.example.wechat_padc.databinding.ViewholderChatListBinding
import com.example.wechat_padc.delegates.ContactsDelegate
import com.example.wechat_padc.utils.convertTimeToRelativeFormat
import com.squareup.picasso.Picasso

class ChatListViewHolder(val itemBinding: ViewholderChatListBinding, delegate: ContactsDelegate) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private var mUserModel: UserModel = UserModelImpl
    private lateinit var mReceiverId:String
    private lateinit var mSenderId:String

    init {
        itemBinding.root.setOnClickListener {
            delegate.onTapContacts(mReceiverId)
        }
    }



    fun bindData(messagedContactID: String, mCurrentUserUid: String) {
        mReceiverId = messagedContactID
        mUserModel.getMessagesOfContact(mCurrentUserUid, messagedContactID,
            onSuccess = {
                itemBinding.tvLastMessage.text= it.last().message
                itemBinding.tvLastChattedTime.text = convertTimeToRelativeFormat(it.last().timeMillis)
            },
            onFailure = {

            })

        mUserModel.getUserData(messagedContactID, onSuccess = {
            itemBinding.tvContactOrGroupName.text = it.name
            Picasso.get()
                .load(it.profile)
                .into(itemBinding.ivContactOrGroupProfile)
        },
        onFailure = {

        })

    }
}