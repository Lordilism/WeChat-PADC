package com.example.wechat_padc.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.wechat_padc.databinding.ActivityLogInBinding
import com.example.wechat_padc.mvp.presenters.LogInPresenterImpl
import com.example.wechat_padc.mvp.view.LogInView

class LogInActivity : BaseActivity(),LogInView {
    //view binding
    private lateinit var binding: ActivityLogInBinding

    private lateinit var mPresenter: LogInPresenterImpl

    companion object{
        fun newIntent(context:Context):Intent{
            return Intent(context,LogInActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpListeners()

        setUpPresenter()
        mPresenter.onUiReady(this)
    }

    private fun setUpListeners() {
        binding.btnLogin.setOnClickListener {
            navigateToMainScreen()
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(LogInPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    override fun navigateToMainScreen() {
        startActivity(MainScreenActivity.newIntent(this))
    }

    override fun showError(message: String) {

    }
}