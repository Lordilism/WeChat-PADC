package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderMomentsBinding
import com.example.wechat_padc.viewholders.MomentsViewHolder


class MomentsAdapter : RecyclerView.Adapter<MomentsViewHolder>() {

    //    private var mData: List<ResponseVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentsViewHolder {
        val itemBinding =
            ViewholderMomentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_moments,parent,false)
        return MomentsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MomentsViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 10
    }


}