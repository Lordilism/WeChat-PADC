package com.example.wechat_padc.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.databinding.ViewholderContactsBinding
import com.example.wechat_padc.delegates.ContactsDelegate
import com.squareup.picasso.Picasso

open class ContactsViewHolder(val itemBinding: ViewholderContactsBinding,val delegate:ContactsDelegate) :
    RecyclerView.ViewHolder(itemBinding.root) {
    private var mMsgReceiverUID = ""
    init {
        itemBinding.root.setOnClickListener {
            delegate.onTapContacts(mMsgReceiverUID)
        }
    }


    fun binData(contactsVO: ContactsVO) {
        itemBinding.tvContactName.text = contactsVO.name
        mMsgReceiverUID = contactsVO.userUID.toString()
        Picasso.get()
            .load(contactsVO.profile)
            .resize(100,100)
            .into(itemBinding.ivActivePersonProfile)
    }

}