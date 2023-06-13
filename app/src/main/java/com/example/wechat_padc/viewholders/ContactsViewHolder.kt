package com.example.wechat_padc.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderContactsBinding

open class ContactsViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    fun binData(contact: String) {
        val binding = ViewholderContactsBinding.bind(itemView)
        binding.tvContactName.text = contact
    }

}