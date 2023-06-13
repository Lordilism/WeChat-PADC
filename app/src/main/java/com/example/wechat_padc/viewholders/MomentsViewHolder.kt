package com.example.wechat_padc.viewholders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.adapters.MomentPhtotoAdapter
import com.example.wechat_padc.databinding.ViewholderMomentsBinding

class MomentsViewHolder(val itemBinding: ViewholderMomentsBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private var mMomentPhotoAdapter = MomentPhtotoAdapter()

    init {
        itemBinding.rvMomentPhotos.adapter = mMomentPhotoAdapter
        itemBinding.rvMomentPhotos.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
    }

}