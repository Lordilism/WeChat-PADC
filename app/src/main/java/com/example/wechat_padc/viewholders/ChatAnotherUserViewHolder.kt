package com.example.wechat_padc.viewholders

import com.example.wechat_padc.databinding.ViewholderAnotherUserBinding

class ChatAnotherUserViewHolder(val itemBinding: ViewholderAnotherUserBinding) :
    BaseViewHolder(itemBinding.root) {
    override fun setData(s: String) {
        itemBinding.tvMessageright.text = s
    }
}