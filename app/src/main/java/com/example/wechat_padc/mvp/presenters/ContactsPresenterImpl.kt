package com.example.wechat_padc.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.data.Models.AuthenticationModel
import com.example.wechat_padc.data.Models.AuthenticationModelImpl
import com.example.wechat_padc.data.Models.UserModel
import com.example.wechat_padc.data.Models.UserModelImpl
import com.example.wechat_padc.data.VO.GroupVO
import com.example.wechat_padc.mvp.view.ContactsView

class ContactsPresenterImpl : ContactsPresenter, ViewModel() {
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val mUserModel: UserModel = UserModelImpl

    private var mView: ContactsView? = null

    override fun intiView(view: ContactsView) {
        mView = view
    }

    override fun onTapCreateGroup() {
        mView?.createGroup()
    }

    override fun onTapClearText() {
        mView?.deleteAllTextFromField()
    }

    override fun onTapNewContact() {
        mView?.openQrScanner()
    }

    override fun addNewContactOnStore(contents: String) {
        val userUIDScanner = mAuthenticationModel.getUserUID()
        mUserModel.addNewContact(userUIDScanner, contents,
            onSuccess = {
                mView?.showSuccessMessage()

            },
            onFailure = {
                mView?.showError(it)
            })

    }


    override fun onUiReady(owner: LifecycleOwner) {
        val userUid= mAuthenticationModel.getUserUID()
        mView?.currentUserUID(userUid)
        mUserModel.getContactsList(userUid,onSuccess = {
            mView?.showContactsList(it)

        },
        onFailure = {
            mView?.showError(it)
        })

        mUserModel.getGroups(onSuccess = {groupVO->
            val listVO = mutableListOf<GroupVO>()
            for (i in groupVO){
                if (i.members.contains(userUid)){
                    listVO.add(i)
                }
            }
            mView?.showGroup(listVO)

        },
        onFailure = {

        })

    }

    override fun onTapContacts(msgReceiverUid: String) {

        mView?.navigateToChatActivity(msgReceiverUid)

    }

    override fun onTapGroup(groupID: String) {
        mView?.navigateToChatActivityFromGroup(groupID)
    }

}