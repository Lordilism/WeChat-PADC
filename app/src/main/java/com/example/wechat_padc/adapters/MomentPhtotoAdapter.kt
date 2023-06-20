package com.example.wechat_padc.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderMomentsPhotoBinding
import com.example.wechat_padc.viewholders.MomentsPhotoViewHolder

class MomentPhtotoAdapter : RecyclerView.Adapter<MomentsPhotoViewHolder>() {
    private var mPhotoList = listOf<String>()
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
        holder.binData(mPhotoList[position])
    }

    override fun getItemCount(): Int {
        return mPhotoList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(photoList:List<String>){
        mPhotoList = photoList
        notifyDataSetChanged()
    }
}