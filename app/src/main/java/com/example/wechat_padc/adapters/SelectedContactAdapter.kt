package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.databinding.ViewholderSelectedContactsBinding
import com.example.wechat_padc.viewholders.SelectedContactsViewHolder

class SelectedContactAdapter: RecyclerView.Adapter<SelectedContactsViewHolder>() {
    private var mSelectedContacts = mutableListOf<ContactsVO>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedContactsViewHolder {
        val itemBinding =
            ViewholderSelectedContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_moments,parent,false)
        return SelectedContactsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SelectedContactsViewHolder, position: Int) {
        holder.bindData(mSelectedContacts[position])
    }

    override fun getItemCount(): Int {
        return mSelectedContacts.count()
    }

    fun setNewData(selectedContactsList:MutableList<ContactsVO>){
        mSelectedContacts = selectedContactsList
        notifyDataSetChanged()
    }
}