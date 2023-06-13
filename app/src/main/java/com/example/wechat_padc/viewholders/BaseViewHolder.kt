package com.example.wechat_padc.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun setData(s: String) {


    }

}