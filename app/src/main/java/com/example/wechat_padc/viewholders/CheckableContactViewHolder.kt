package com.example.wechat_padc.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.R
import com.example.wechat_padc.databinding.ViewholderCheckableContactBinding

class CheckableContactViewHolder(val itemBinding: ViewholderCheckableContactBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    private var UNSELECTED = true

    init {
        itemBinding.root.setOnClickListener {
            when (UNSELECTED) {
                true -> {
                    itemBinding.ivCheckContact.setImageResource(R.drawable.ic_checked)
                    UNSELECTED = !UNSELECTED
                }
                false -> {
                    itemBinding.ivCheckContact.setImageResource(R.drawable.bkg_checkable)
                    UNSELECTED = !UNSELECTED
                }

            }

        }

    }

    fun bindData(contact: String) {
        itemBinding.tvContactName.text = contact

    }

}