package com.example.wechat_padc.viewholders

import com.example.wechat_padc.databinding.ViewholderCurrentUserBinding

class ChatCurrentUserVieweHolder(val itemBinding: ViewholderCurrentUserBinding) :
    BaseViewHolder(itemBinding.root) {
    //    private var mMessageAdapter = MessageAdapter()
//    init {
//        itemView.rvImageList.adapter = mMessageAdapter
//        itemView.rvImageList.layoutManager = LinearLayoutManager(itemView.context,LinearLayoutManager.VERTICAL,false)
//    }
    override fun setData(s: String) {
        itemBinding.tvMessageright.text = s
    }


}