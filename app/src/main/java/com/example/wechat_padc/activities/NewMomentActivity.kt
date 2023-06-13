package com.example.wechat_padc.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.wechat_padc.databinding.ActivityNewMomentBinding
import com.example.wechat_padc.mvp.presenters.NewMomentPresenterImpl
import com.example.wechat_padc.mvp.view.NewMomentView

class NewMomentActivity : BaseActivity(), NewMomentView {
    private lateinit var binding:ActivityNewMomentBinding
    private lateinit var mPresenter: NewMomentPresenterImpl

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context,NewMomentActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMomentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpPresenter()
        setUpListerners()

        mPresenter.onUiReady(this)

    }

    private fun setUpListerners() {
        binding.btnClose.setOnClickListener {
            closeMomentCreate()
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(NewMomentPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    override fun closeMomentCreate() {
        super.onBackPressed()
    }

    override fun showError(message: String) {

    }
}