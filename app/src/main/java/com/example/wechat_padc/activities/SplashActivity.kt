package com.example.wechat_padc.activities

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.wechat_padc.databinding.ActivitySplashBinding
import com.example.wechat_padc.mvp.presenters.SplashPresenter
import com.example.wechat_padc.mvp.presenters.SplashPresenterImpl
import com.example.wechat_padc.mvp.view.SplashView


class SplashActivity : BaseActivity(), SplashView {

    //Presenters
    private lateinit var mPresenter: SplashPresenter
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpPresenter()
        setUpListeners()
        mPresenter.onUiReady(this)
    }

    private fun setUpListeners() {

        // Log in Button
        binding.btnLoginSplash.setOnClickListener {
            navigateToLogInScreen()
        }

        // Sign Up Button
        binding.btnSignUpSplash.setOnClickListener {
            navigateToSignUpScreen()
        }


    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[SplashPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    override fun showSplashScreen() {

        Toast.makeText(this, "showing splash screen", Toast.LENGTH_SHORT).show()

    }

    override fun navigateToLogInScreen() {

        startActivity(LogInActivity.newIntent(this))

    }

    override fun navigateToSignUpScreen() {
        startActivity(SignUpActivity.newIntent(this))


    }

    override fun showError(message: String) {

    }
}