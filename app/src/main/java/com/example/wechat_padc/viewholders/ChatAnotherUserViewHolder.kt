package com.example.wechat_padc.viewholders

import com.example.wechat_padc.data.VO.MessageVO
import com.example.wechat_padc.databinding.ViewholderAnotherUserBinding
import com.example.wechat_padc.utils.convertMillisTo12HourFormat

class ChatAnotherUserViewHolder(val itemBinding: ViewholderAnotherUserBinding) :
    BaseViewHolder(itemBinding.root) {
    override fun setData(messageVo: MessageVO) {
        itemBinding.tvMessageright.text = messageVo.message
        itemBinding.tvTimeSent.text = convertMillisTo12HourFormat(messageVo.timeMillis)
    }
}