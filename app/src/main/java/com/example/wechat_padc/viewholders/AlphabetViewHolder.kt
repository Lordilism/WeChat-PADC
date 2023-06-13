package com.example.wechat_padc.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderAlphabetBinding


class AlphabetViewHolder(val itemBinding: ViewholderAlphabetBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(alphabet: String) {
        itemBinding.tvAlphabet.text = alphabet

    }




}