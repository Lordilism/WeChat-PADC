package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderChatListBinding
import com.example.wechat_padc.delegates.ContactsDelegate
import com.example.wechat_padc.viewholders.ChatListViewHolder

class ChatListAdapter(val delegate: ContactsDelegate) : RecyclerView.Adapter<ChatListViewHolder>() {
    private var mMessagedContactID= listOf<String>()
    private var mCurrentUserUid:String =""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {

        val itemBinding =
            ViewholderChatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.viewholder_chat_list, parent, false)
        return ChatListViewHolder(itemBinding,delegate)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.bindData(mMessagedContactID[position],mCurrentUserUid)
    }

    override fun getItemCount(): Int {
        return mMessagedContactID.count()

    }

    fun setNewData(messageList: List<String>, signInUserUID: String) {
        mMessagedContactID = messageList
        mCurrentUserUid = signInUserUID
        notifyDataSetChanged()
    }
}