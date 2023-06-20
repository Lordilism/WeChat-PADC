package com.example.wechat_padc.viewholders

import com.example.wechat_padc.data.VO.MessageVO
import com.example.wechat_padc.databinding.ViewholderCurrentUserBinding
import com.example.wechat_padc.utils.convertMillisTo12HourFormat
import com.squareup.picasso.Picasso

class ChatCurrentUserVieweHolder(val itemBinding: ViewholderCurrentUserBinding) :
    BaseViewHolder(itemBinding.root) {
    //    private var mMessageAdapter = MessageAdapter()
//    init {
//        itemView.rvImageList.adapter = mMessageAdapter
//        itemView.rvImageList.layoutManager = LinearLayoutManager(itemView.context,LinearLayoutManager.VERTICAL,false)
//    }
    override fun setData(messageVo: MessageVO) {
        itemBinding.tvMessageright.text = messageVo.message
        itemBinding.tvFriendName.text = messageVo.name
        itemBinding.tvTimeSent.text = convertMillisTo12HourFormat(messageVo.timeMillis)
        Picasso.get()
            .load(messageVo.profile)
            .resize(100,100)
            .into(itemBinding.ivFriendProfile)
    }


}