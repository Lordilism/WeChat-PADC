package com.example.wechat_padc.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderSelectedPhotoBinding
import com.squareup.picasso.Picasso

class SelectedPhotoViewHolder(
    val itemBinding: ViewholderSelectedPhotoBinding,
    val context: Context
) : RecyclerView.ViewHolder(itemBinding.root) {


    fun bindData(image: String) {

//        val url = GlideUrl(image)
        Picasso.get()
            .load(image)
            .resize(300,300)
            .into(itemBinding.ivUploadSelectedImage)

    }
}