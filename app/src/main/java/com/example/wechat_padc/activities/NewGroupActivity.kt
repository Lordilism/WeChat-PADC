package com.example.wechat_padc.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.adapters.CheckableContactAdapter
import com.example.wechat_padc.adapters.SelectedContactAdapter
import com.example.wechat_padc.databinding.ActivityNewGroupBinding
import com.example.wechat_padc.dummy.contactsSampleList
import com.example.wechat_padc.mvp.presenters.NewGroupPresenterImpl
import com.example.wechat_padc.mvp.view.NewGroupView

class NewGroupActivity : BaseActivity() ,NewGroupView{
    private lateinit var binding: ActivityNewGroupBinding

    private lateinit var mCheckableAdapter: CheckableContactAdapter
    private lateinit var mSelectedContactAdapter: SelectedContactAdapter

    private lateinit var mPresenter: NewGroupPresenterImpl

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
    }

    private fun setUpListenters() {
        binding.btnCreate.setOnClickListener {
            createGroup()
        }

        binding.btnClose.setOnClickListener {
            cancelGroupCreation()
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
        mSelectedContactAdapter = SelectedContactAdapter()
        binding.rvSelectedContactsNewGroup.adapter = mSelectedContactAdapter
        binding.rvSelectedContactsNewGroup.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


        mCheckableAdapter = CheckableContactAdapter(contactsSampleList.sorted())
        binding.rvCheckableContactsNewGroup.adapter = mCheckableAdapter
        binding.rvCheckableContactsNewGroup.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    override fun cancelGroupCreation() {
        super.onBackPressed()
    }

    override fun createGroup() {


    }

    override fun deleteAllTextFromField() {
        binding.tvSearch.text.clear()

    }

    override fun showError(message: String) {

    }


}