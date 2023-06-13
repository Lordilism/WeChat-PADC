package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.R
import com.example.wechat_padc.databinding.ViewholderGroupBinding
import com.example.wechat_padc.viewholders.GroupViewHolder

class GroupAdapter:RecyclerView.Adapter<GroupViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val itemBinding = ViewholderGroupBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_group,parent,false)
        return GroupViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 7
    }

}