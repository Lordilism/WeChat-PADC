package com.example.wechat_padc.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.MessageVO

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun setData(messageVo: MessageVO) {


    }

}