package com.example.wechat_padc.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.databinding.ViewholderSelectedContactsBinding
import com.squareup.picasso.Picasso

class SelectedContactsViewHolder(val itemBinding: ViewholderSelectedContactsBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bindData(contactsVO: ContactsVO) {

        itemBinding.tvSelectedContactName.text = contactsVO.name
        Picasso.get()
            .load(contactsVO.profile)
            .resize(150,150)
            .into(itemBinding.ivSelectedContactProfile)

    }
}