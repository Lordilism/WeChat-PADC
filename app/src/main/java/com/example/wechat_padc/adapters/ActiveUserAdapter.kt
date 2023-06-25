package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.databinding.ViewholderActiveUserBinding
import com.example.wechat_padc.viewholders.ActiveUserViewHolder

class ActiveUserAdapter: RecyclerView.Adapter<ActiveUserViewHolder>() {
    private var mContactVOList = listOf<ContactsVO>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveUserViewHolder {
        val itemBinding = ViewholderActiveUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_active_user,parent,false)
        return ActiveUserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ActiveUserViewHolder, position: Int) {
            holder.bindData(mContactVOList[position])
    }

    override fun getItemCount(): Int {
        return mContactVOList.size
    }

    fun setNewData(listContactVO: List<ContactsVO>) {
        mContactVOList = listContactVO
        notifyDataSetChanged()

    }
}