package com.example.wechat_padc.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.wechat_padc.databinding.ActivityLogInBinding
import com.example.wechat_padc.mvp.presenters.LogInPresenterImpl
import com.example.wechat_padc.mvp.view.LogInView
import com.google.android.material.snackbar.Snackbar

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
            val email = binding.etPhoneNumberLogin.text.toString().trim()
            val password = binding.etPasswordLogin.text.toString().trim()
            mPresenter.onTapLogIn(email,password)
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
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_SHORT).show()
    }
}