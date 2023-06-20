package com.example.wechat_padc.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.databinding.ViewholderCheckableContactBinding
import com.example.wechat_padc.delegates.SelectedContactsDelegate
import com.example.wechat_padc.viewholders.CheckableContactViewHolder

class CheckableContactAdapter(private val delegate: SelectedContactsDelegate) :
    RecyclerView.Adapter<CheckableContactViewHolder>() {
    private var mContactsList = listOf<ContactsVO>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckableContactViewHolder {
        val itemBinding =
            ViewholderCheckableContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CheckableContactViewHolder(itemBinding,delegate)
    }

    override fun onBindViewHolder(holder: CheckableContactViewHolder, position: Int) {
        holder.bindData(mContactsList[position])

    }

    override fun getItemCount(): Int {
        return mContactsList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(listContactsVO: List<ContactsVO>) {
        mContactsList = listContactsVO
        notifyDataSetChanged()
    }
}