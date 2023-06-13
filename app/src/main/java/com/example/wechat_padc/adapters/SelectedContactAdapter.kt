package com.example.wechat_padc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderSelectableCotactsBinding
import com.example.wechat_padc.viewholders.SelectedContactViewHolder

class SelectedContactAdapter:RecyclerView.Adapter<SelectedContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedContactViewHolder {
        val itemBinding = ViewholderSelectableCotactsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SelectedContactViewHolder(itemBinding   )
    }

    override fun onBindViewHolder(holder: SelectedContactViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }
}