package com.example.wechat_padc.viewholders

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.adapters.MomentPhtotoAdapter
import com.example.wechat_padc.data.VO.MomentsVO
import com.example.wechat_padc.databinding.ViewholderMomentsBinding
import com.example.wechat_padc.utils.convertMillisTo12HourFormat
import com.squareup.picasso.Picasso

class MomentsViewHolder(val itemBinding: ViewholderMomentsBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    private var mMomentPhotoAdapter = MomentPhtotoAdapter()
    var mPhotoList = listOf<String>()
    init {
        itemBinding.rvMomentPhotos.adapter = mMomentPhotoAdapter
        itemBinding.rvMomentPhotos.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
//        mMomentPhotoAdapter.setNewData(mPhotoList)

    }

    fun setNewData(momentsVO: MomentsVO) {
        itemBinding.tvContent.text = momentsVO.content
        itemBinding.tvUserNameMoments.text = momentsVO.userName
        itemBinding.tvPostedTimeMoments.text = convertMillisTo12HourFormat(momentsVO.millis!!)
        Picasso.get()
            .load(momentsVO.userProfile)
            .resize(150,150)
            .into(itemBinding.ivProfileMoments)

        val photo = momentsVO.photoList.split(", ")
        mPhotoList = photo

        Log.d("list", mPhotoList.toString())

        mMomentPhotoAdapter.setNewData(mPhotoList)

    }





}