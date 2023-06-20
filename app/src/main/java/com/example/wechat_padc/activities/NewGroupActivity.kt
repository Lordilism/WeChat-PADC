package com.example.wechat_padc.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.adapters.CheckableContactAdapter
import com.example.wechat_padc.adapters.SelectedContactAdapter
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.databinding.ActivityNewGroupBinding
import com.example.wechat_padc.mvp.presenters.NewGroupPresenterImpl
import com.example.wechat_padc.mvp.view.NewGroupView

class NewGroupActivity : BaseActivity() ,NewGroupView{
    private lateinit var binding: ActivityNewGroupBinding

    private lateinit var mCheckableAdapter: CheckableContactAdapter
    private lateinit var mSelectedContactsAdapter: SelectedContactAdapter


    private lateinit var mPresenter: NewGroupPresenterImpl

    private var mCurrentUserId = ""

    private var mSelectedContactsList= mutableListOf<ContactsVO>()
    private var members = mutableListOf<String>()

    companion object{
        fun newIntent(context:Context): Intent {
            return Intent(context,NewGroupActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewGroupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpPresenter()
        setUpListenters()

        setUpAdapters()
        mPresenter.onUiReady(this)
    }

    private fun setUpListenters() {
        binding.btnCreate.setOnClickListener {

            val groupName = binding.etGroupName.text.toString()
            mPresenter.onTapCreate(groupName,mSelectedContactsList,mCurrentUserId,System.currentTimeMillis())
        }

        binding.btnClose.setOnClickListener {
            mPresenter.onTapClose()
        }
        binding.ivClear.setOnClickListener {
            deleteAllTextFromField()
        }


    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(NewGroupPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    private fun setUpAdapters() {
        mCheckableAdapter = CheckableContactAdapter(mPresenter)
        binding.rvCheckableContactsNewGroup.adapter = mCheckableAdapter
        binding.rvCheckableContactsNewGroup.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        mSelectedContactsAdapter = SelectedContactAdapter()
        binding.rvSelectedContactsNewGroup.adapter = mSelectedContactsAdapter
        binding.rvSelectedContactsNewGroup.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    override fun cancelGroupCreation() {
        super.onBackPressed()
    }

    override fun createGroup() {
        super.onBackPressed()


    }

    override fun deleteAllTextFromField() {
        binding.tvSearch.text.clear()

    }

    override fun showContactsList(listContactsVO: List<ContactsVO>) {
        mCheckableAdapter.setNewData(listContactsVO)
    }

    override fun showSelectedContactList(selectedContactList: ContactsVO) {
        mSelectedContactsList.add(selectedContactList)
        Log.d("contacts", mSelectedContactsList.toString())
        mSelectedContactsAdapter.setNewData(mSelectedContactsList)

    }

    override fun showUserData(userUID: String) {
        mCurrentUserId = userUID
    }

    override fun showError(message: String) {

    }


}