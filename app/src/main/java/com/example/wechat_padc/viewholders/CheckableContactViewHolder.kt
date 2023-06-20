package com.example.wechat_padc.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.databinding.ViewholderCheckableContactBinding
import com.example.wechat_padc.delegates.SelectedContactsDelegate
import com.squareup.picasso.Picasso

class CheckableContactViewHolder(
    val itemBinding: ViewholderCheckableContactBinding,
    delegate: SelectedContactsDelegate
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private var mContatsVo: ContactsVO? = null
    private var UNSELECTED = true


    init {

        itemBinding.root.setOnClickListener {
            delegate.onTapContacts(mContatsVo!!)


        }

    }

    fun bindData(contactVO: ContactsVO) {
        mContatsVo = contactVO
        itemBinding.tvContactName.text = contactVO.name
        Picasso.get()
            .load(contactVO.profile)
            .resize(150, 150)
            .into(itemBinding.ivProfile)

    }

}