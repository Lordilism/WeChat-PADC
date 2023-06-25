package com.example.wechat_padc.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.data.Models.UserModel
import com.example.wechat_padc.data.Models.UserModelImpl
import com.example.wechat_padc.databinding.ViewholderChatListBinding
import com.example.wechat_padc.delegates.GroupDelegate
import com.example.wechat_padc.utils.convertTimeToRelativeFormat
import com.squareup.picasso.Picasso

class GroupMessageViewHolder(
    val itemBinding: ViewholderChatListBinding,
    delegate: GroupDelegate,

    ) : RecyclerView.ViewHolder(itemBinding.root) {
    private lateinit var mGroupId:String
    private val mUserModel: UserModel = UserModelImpl

    init {
        itemBinding.root.setOnClickListener {
            delegate.onTapGroup(mGroupId)
        }
    }



    fun bindData(joinedGroupId: Long) {

        mGroupId = joinedGroupId.toString()
        mUserModel.getGroupInfo(joinedGroupId.toString(), onSucces = {
            itemBinding.tvContactOrGroupName.text = it.groupName
            Picasso.get()
                .load(it.groupLogo)
                .into(itemBinding.ivContactOrGroupProfile2)

        },
            onFailure = {

            })

        mUserModel.getMesssagesGroup(joinedGroupId.toString(), onSuccess = {
            itemBinding.tvLastMessage.text = it.last().message
            itemBinding.tvLastChattedTime.text = convertTimeToRelativeFormat(it.last().timeMillis)

        },
            onFailure = {

            })
    }

}