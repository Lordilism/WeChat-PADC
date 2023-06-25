package com.example.wechat_padc.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.databinding.ViewholderActiveUserBinding
import com.squareup.picasso.Picasso

class ActiveUserViewHolder(val itemBinding: ViewholderActiveUserBinding) :RecyclerView.ViewHolder(itemBinding.root) {
    fun bindData(contactsVO: ContactsVO) {
        itemBinding.tvActivePersonNameChat.text = contactsVO.name
        Picasso.get()
            .load(contactsVO.profile)
            .resize(100,100)
            .into(itemBinding.ivActivePersonProfileChat)
    }
}