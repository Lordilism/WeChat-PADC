package com.example.wechat_padc.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderSelectedPhotoBinding
import com.example.wechat_padc.viewholders.SelectedPhotoViewHolder

class SelectedPhotoAdapter(private val context:Context): RecyclerView.Adapter<SelectedPhotoViewHolder>() {
    private var photoList = mutableListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedPhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewholderSelectedPhotoBinding.inflate(inflater)
        return SelectedPhotoViewHolder(binding,context)
    }

    override fun onBindViewHolder(holder: SelectedPhotoViewHolder, position: Int) {
        holder.bindData(photoList[position])


//        Glide.with(holder.itemView.context)
//            .load(photoList[position])
//            .into(holder.itemBinding.ivUploadSelectedImage)


    }

    override fun getItemCount(): Int {
//        return photoList.size
        return photoList.count()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(image: MutableList<String>) {
        photoList = image
        notifyDataSetChanged()




    }
}