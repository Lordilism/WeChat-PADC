package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderChatListBinding
import com.example.wechat_padc.viewholders.ChatListViewHolder

class ChatListAdapter : RecyclerView.Adapter<ChatListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {

        val itemBinding =
            ViewholderChatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.viewholder_chat_list, parent, false)
        return ChatListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5

    }
}