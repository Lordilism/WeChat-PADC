package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderCheckableContactBinding
import com.example.wechat_padc.viewholders.CheckableContactViewHolder

class CheckableContactAdapter(private val contacts: List<String>) :
    RecyclerView.Adapter<CheckableContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckableContactViewHolder {
        val itemBinding =
            ViewholderCheckableContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CheckableContactViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CheckableContactViewHolder, position: Int) {
        holder.bindData(contacts[position])

    }

    override fun getItemCount(): Int {
        return contacts.count()
    }
}