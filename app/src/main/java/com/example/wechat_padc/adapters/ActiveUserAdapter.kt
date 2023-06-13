package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.R
import com.example.wechat_padc.databinding.ViewholderActiveUserBinding
import com.example.wechat_padc.viewholders.ActiveUserViewHolder

class ActiveUserAdapter: RecyclerView.Adapter<ActiveUserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveUserViewHolder {
        val itemBinding = ViewholderActiveUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_active_user,parent,false)
        return ActiveUserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ActiveUserViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}