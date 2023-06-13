package com.example.wechat_padc.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.wechat_padc.databinding.ActivitySignUpBinding
import com.example.wechat_padc.mvp.presenters.SignUpPresenter
import com.example.wechat_padc.mvp.presenters.SignUpPresenterImpl
import com.example.wechat_padc.mvp.view.SignUpView

class SignUpActivity : BaseActivity(),SignUpView {
    private lateinit var binding: ActivitySignUpBinding

    //presenter
    private lateinit var mPresenter: SignUpPresenter

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context,SignUpActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpPresenter()
        setUpListeners()

        mPresenter.onUiReady(this)

    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[SignUpPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListeners() {
        binding.btnSignUpWithEmailSplash.setOnClickListener {
            navigateToCreateProfileWithEmail()
        }
    }

    override fun navigateToCreateProfileWithEmail() {
        startActivity(CreateProfileActivity.newIntent(this))
    }

    override fun showError(message: String) {

    }
}