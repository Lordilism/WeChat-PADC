package com.example.wechat_padc.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderMomentsPhotoBinding
import com.squareup.picasso.Picasso

class MomentsPhotoViewHolder(val itemBinding: ViewholderMomentsPhotoBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun binData(photo: String) {
        photo
        Picasso.get()
            .load(photo)
            .resize(500,500)
            .into(itemBinding.ivMomentPhoto)

    }
}