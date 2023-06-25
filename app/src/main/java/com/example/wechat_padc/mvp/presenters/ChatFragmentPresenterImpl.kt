package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.data.Models.AuthenticationModel
import com.example.wechat_padc.data.Models.AuthenticationModelImpl
import com.example.wechat_padc.data.Models.UserModel
import com.example.wechat_padc.data.Models.UserModelImpl
import com.example.wechat_padc.mvp.view.ChatFragmentView

class ChatFragmentPresenterImpl : ChatFragmentPresenter, ViewModel() {
    private var mView: ChatFragmentView? = null
    private val mUserModel: UserModel = UserModelImpl
    private lateinit var mSenderID:String
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    override fun initView(view: ChatFragmentView) {
        mView = view
    }


    override fun onUiReady(owner: LifecycleOwner) {
        mSenderID = mAuthenticationModel.getUserUID()

        mUserModel.getContactsList(mSenderID, onSuccess = {
            mView?.showActiveUser(it)
        },
            onFailure = {
                mView?.showError(it)
            })

        mUserModel.getLatestMessage(mSenderID, onSuccess = {
            mView?.showLatestMessage(it, mSenderID)
        },
            onFailure = {
                mView?.showError(it)
            })

        mUserModel.getGroups(onSuccess = {groupList->
                val joinedGroupID:MutableSet<Long> = mutableSetOf()
                groupList.forEach {group->
                    group.members.forEach { members->
                        if (members == mSenderID){
                            joinedGroupID.add(group.groupID)
                        }
                    }
                }

            mView?.showJoindGroups(joinedGroupID)




        },
            onFailure = {

            })

    }

    override fun onTapContacts(msgReceiverUid: String) {
        mView?.navigateToChat(msgReceiverUid,mSenderID)

    }

    override fun onTapGroup(groupID: String) {
        mView?.navigateToChatGroup(groupID,mSenderID)
    }
}