package com.example.wechat_padc.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.MessageVO
import com.example.wechat_padc.databinding.ViewholderAnotherUserBinding
import com.example.wechat_padc.databinding.ViewholderCurrentUserBinding
import com.example.wechat_padc.viewholders.BaseViewHolder
import com.example.wechat_padc.viewholders.ChatAnotherUserViewHolder
import com.example.wechat_padc.viewholders.ChatCurrentUserVieweHolder

class MessageAdapter(val currentUID: String) : RecyclerView.Adapter<BaseViewHolder>() {
    private var mMessageVOList = mutableListOf<MessageVO>()
    val VIEW_TYPE_CURRENT_USER = 0
    val VIEW_TYPE_ANOTHER_USER = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_CURRENT_USER -> {
                val itemBinding = ViewholderCurrentUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_current_user,parent,false)
                return ChatCurrentUserVieweHolder(itemBinding)
            }
            VIEW_TYPE_ANOTHER_USER -> {
                val itemBinding = ViewholderAnotherUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_another_user,parent,false)
                return ChatAnotherUserViewHolder(itemBinding)
            }
            else -> {
                throw IllegalArgumentException("Out of ViewType")
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.setData(mMessageVOList[position])


    }

    override fun getItemCount(): Int {
        return mMessageVOList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(messageVoList: MutableList<MessageVO>) {
        mMessageVOList = messageVoList
        notifyDataSetChanged()

    }

    override fun getItemViewType(position: Int): Int {
//            return when (mMessageVOList[position].userUid) {
//                currentUID ->
//                    VIEW_TYPE_CURRENT_USER
//                else ->
//                    VIEW_TYPE_ANOTHER_USER
//            }
        return if (mMessageVOList[position].userUid == currentUID) {
            VIEW_TYPE_ANOTHER_USER
        } else {

            VIEW_TYPE_CURRENT_USER
        }


    }


}