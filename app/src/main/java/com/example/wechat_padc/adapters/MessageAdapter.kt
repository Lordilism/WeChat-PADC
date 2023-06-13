package com.example.wechat_padc.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.R
import com.example.wechat_padc.databinding.ViewholderAnotherUserBinding
import com.example.wechat_padc.databinding.ViewholderCurrentUserBinding
import com.example.wechat_padc.viewholders.BaseViewHolder
import com.example.wechat_padc.viewholders.ChatAnotherUserViewHolder
import com.example.wechat_padc.viewholders.ChatCurrentUserVieweHolder

class MessageAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    private var mDummyMessage = listOf<String>()
    private var mDummyMessageForAnother = listOf<String>()
    val VIEW_TYPE_CURRENT_USER = 0
    val VIEW_TYPE_ANOTHER_USER = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when(viewType){
            VIEW_TYPE_CURRENT_USER->{
                val itemBinding = ViewholderCurrentUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_current_user,parent,false)
                return ChatCurrentUserVieweHolder(itemBinding)
            }
            VIEW_TYPE_ANOTHER_USER->{
                val itemBinding = ViewholderAnotherUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_another_user,parent,false)
                return ChatAnotherUserViewHolder(itemBinding)
            }
            else->{
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_current_user,parent,false)
                val itemBinding = ViewholderCurrentUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return ChatCurrentUserVieweHolder(itemBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.setData(mDummyMessage[position])
        holder.setData(mDummyMessageForAnother[position])


    }

    override fun getItemCount(): Int {
        return mDummyMessage.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(data:List<String>){
        mDummyMessage = data
        notifyDataSetChanged()

    }

    override fun getItemViewType(position: Int): Int {
        return when{
            position%2 == 0->{
                VIEW_TYPE_ANOTHER_USER
            }else->{
                VIEW_TYPE_CURRENT_USER
            }
        }

    }

    fun setNewDataForAnother(data: List<String>) {
       mDummyMessageForAnother = data
       notifyDataSetChanged()
    }
}