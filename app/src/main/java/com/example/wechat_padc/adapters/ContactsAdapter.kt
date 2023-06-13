package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderContactsBinding
import com.example.wechat_padc.viewholders.ContactsViewHolder

class ContactsAdapter(private val contacts: List<String>) :
    RecyclerView.Adapter<ContactsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewholderContactsBinding.inflate(inflater)
        return ContactsViewHolder(binding.root)




    }
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_contacts,parent,false)


    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {

        holder.binData(contacts[position])

    }

    override fun getItemCount(): Int {
        return contacts.count()
    }




}