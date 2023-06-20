package com.example.wechat_padc.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.GroupVO
import com.example.wechat_padc.databinding.ViewholderGroupBinding
import com.example.wechat_padc.delegates.GroupDelegate

class GroupViewHolder(val itemBinding: ViewholderGroupBinding,val  delegate: GroupDelegate) : RecyclerView.ViewHolder(itemBinding.root) {
    private var vo =GroupVO()
    init {
        itemBinding.root.setOnClickListener {
            delegate.onTapGroup(vo.groupID.toString())
        }
    }
    fun bindData(groupVO: GroupVO) {
        vo = groupVO
        itemBinding.tvGroupName.text = groupVO.groupName
    }
}