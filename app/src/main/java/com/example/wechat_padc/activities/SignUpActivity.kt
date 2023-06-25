package com.example.wechat_padc.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
        binding.btnVerify.setOnClickListener {
            val emailForm = binding.etEmailForSignUp.text.toString().trim()
            val phoneNo = binding.etPhoneNumberSignUp.text.toString().trim()
            if (emailForm.isBlank()){
                showError("Please Fill Require Field")
            }else if(phoneNo.isBlank()){
                showError("Please Fill Require Field")
            }else{
                navigateToCreateProfileWithEmail(emailForm,phoneNo)
            }

        }
    }

    override fun navigateToCreateProfileWithEmail(emailForm: String, phoneNo: String) {
        startActivity(CreateProfileActivity.newIntent(this, email = emailForm, userPhoneNo = phoneNo))
    }

    override fun showError(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
    }
}