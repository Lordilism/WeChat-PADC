package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderMomentsPhotoBinding
import com.example.wechat_padc.viewholders.MomentsPhotoViewHolder

class MomentPhtotoAdapter : RecyclerView.Adapter<MomentsPhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentsPhotoViewHolder {
        val itemBinding = ViewholderMomentsPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_moments_photo,parent,false)
        return MomentsPhotoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MomentsPhotoViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 2
    }
}