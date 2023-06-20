package com.example.wechat_padc.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderGroupMessageBinding
import com.example.wechat_padc.delegates.GroupDelegate

class GroupMessageViewHolder(
    val itemBinding: ViewholderGroupMessageBinding,
    val delegate: GroupDelegate
) : RecyclerView.ViewHolder(itemBinding.root) {

}