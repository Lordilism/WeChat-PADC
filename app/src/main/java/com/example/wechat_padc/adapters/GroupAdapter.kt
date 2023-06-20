package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.GroupVO
import com.example.wechat_padc.databinding.ViewholderGroupBinding
import com.example.wechat_padc.delegates.GroupDelegate
import com.example.wechat_padc.viewholders.GroupViewHolder

class GroupAdapter(private val delegate: GroupDelegate):RecyclerView.Adapter<GroupViewHolder>() {
    private var mGroupVOList = mutableListOf<GroupVO>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val itemBinding = ViewholderGroupBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_group,parent,false)
        return GroupViewHolder(itemBinding,delegate)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bindData(mGroupVOList[position])

    }

    override fun getItemCount(): Int {
        return mGroupVOList.count()
    }

    fun setNewData(listVO: MutableList<GroupVO>) {
        mGroupVOList = listVO
        notifyDataSetChanged()

    }

}