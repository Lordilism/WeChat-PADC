package com.example.wechat_padc.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.VO.MomentsVO
import com.example.wechat_padc.databinding.ViewholderMomentsBinding
import com.example.wechat_padc.viewholders.MomentsViewHolder


class MomentsAdapter : RecyclerView.Adapter<MomentsViewHolder>() {

    //    private var mData: List<ResponseVO> = listOf()
    private var mMomentsVO = listOf<MomentsVO>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentsViewHolder {
        val itemBinding =
            ViewholderMomentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_moments,parent,false)
        return MomentsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MomentsViewHolder, position: Int) {
        holder.setNewData(mMomentsVO[position])

    }

    override fun getItemCount(): Int {
        return mMomentsVO.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(moments: List<MomentsVO>){
        mMomentsVO = moments

        notifyDataSetChanged()
    }


}