package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderGroupMessageBinding
import com.example.wechat_padc.delegates.GroupDelegate
import com.example.wechat_padc.viewholders.GroupMessageViewHolder

class GroupMessageAdapter(val delegate: GroupDelegate): RecyclerView.Adapter<GroupMessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupMessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewholderGroupMessageBinding.inflate(inflater,parent,false)
        return GroupMessageViewHolder(binding,delegate)
    }

    override fun onBindViewHolder(holder: GroupMessageViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }
}