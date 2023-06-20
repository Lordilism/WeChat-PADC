package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.databinding.ViewholderContactsBinding
import com.example.wechat_padc.delegates.ContactsDelegate
import com.example.wechat_padc.viewholders.ContactsViewHolder

class ContactsAdapter(private val delegate:ContactsDelegate) : RecyclerView.Adapter<ContactsViewHolder>() {
    private var mContactsList = mutableListOf<ContactsVO>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewholderContactsBinding.inflate(inflater)
        return ContactsViewHolder(binding,delegate)


    }
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_contacts,parent,false)


    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.binData(mContactsList[position])


    }

    override fun getItemCount(): Int {
        return mContactsList.count()
    }

    fun setNewData(contactsList: List<ContactsVO>) {
        mContactsList = contactsList as MutableList<ContactsVO>
        notifyDataSetChanged()

    }


}