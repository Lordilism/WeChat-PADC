package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.data.Models.AuthenticationModel
import com.example.wechat_padc.data.Models.AuthenticationModelImpl
import com.example.wechat_padc.data.Models.UserModel
import com.example.wechat_padc.data.Models.UserModelImpl
import com.example.wechat_padc.data.VO.MessageVO
import com.example.wechat_padc.data.VO.UserVO
import com.example.wechat_padc.mvp.view.ChatView

class ChatPresenterImpl : ChatPresenter, ViewModel() {
    private val mUserModel: UserModel = UserModelImpl
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private var mView: ChatView? = null

    private lateinit var userUID: String

    private var mUserVo: UserVO? = null

    override fun initView(view: ChatView) {
        mView = view
    }

    override fun onTapTextSend(msg: String, receiverUID: String, timeMillis: Long) {

        val uid = mAuthenticationModel.getUserUID()
        mUserVo?.let {
            mUserModel.sendText(
                it, uid, receiverUID, msg, timeMillis, onSuccess = {

                    mView?.clearText()
                },
                onFailure = {
                    mView?.showError(it)
                })
        }

    }

    override fun onUIReady(owner: LifecycleOwner, msgReceiver: String) {
        userUID = mAuthenticationModel.getUserUID()
        mUserModel.getUserData(userUID, onSuccess = {
            mUserVo = it
        },
            onFailure = {
                mView?.showError(it)
            })

        mUserModel.getUserData(msgReceiver, onSuccess = {
            mView?.showTappedUserData(it)
        },
            onFailure = {

            })


        mUserModel.getMessages(userUID, receiverUID = msgReceiver, onSuccess = {
            mView?.showMessages(it)

        },
            onFailure = {
                mView?.showError(it)
            })
    }

    override fun onUIReadyForGroup(owner: LifecycleOwner, groupID: String) {
        userUID = mAuthenticationModel.getUserUID()
        mUserModel.getUserData(userUID, onSuccess = {
            mUserVo = it
        },
            onFailure = {
                mView?.showError(it)
            })


        mUserModel.getMesssagesGroup(groupID, onSuccess = {
            mView?.showMessages(it as MutableList<MessageVO>)

        }, onFailure = {
            mView?.showError(it)
        })

        mUserModel.getGroupInfo(groupID,onSucces={
            mView?.showGroupInfo(it)
        },
        onFailure={
            mView?.showError(it)
        })


    }

    override fun onTapSendGroupMessage(msg: String, timeStamp: Long, groupID: String) {
//        val userUid = mAuthenticationModel.getUserUID()
        val vo = MessageVO(mUserVo?.name, timeStamp, userUID, msg, mUserVo?.profile)
        mUserModel.sendTextGroup(vo, groupID, onSuccess = {
            mView?.clearText()
        },
            onFailure = {
                mView?.showError(it)
            })



    }


    override fun onUiReady(owner: LifecycleOwner) {
    }

    override fun onTapChat() {
    }
}