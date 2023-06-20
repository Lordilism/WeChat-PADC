package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.wechat_padc.delegates.ChatDelegate
import com.example.wechat_padc.mvp.view.ChatView

interface ChatPresenter:IBasePresenter,ChatDelegate {
    fun initView(view:ChatView)

    fun onTapTextSend(msg:String,receiverUID:String,timeMillis:Long )

    fun onUIReady(owner: LifecycleOwner, msgReceiver:String)
    fun onUIReadyForGroup(owner: LifecycleOwner, groupID: String)

    fun onTapSendGroupMessage(msg:String,timeStamp:Long,groupID: String)


}