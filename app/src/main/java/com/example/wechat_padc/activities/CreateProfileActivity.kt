package com.example.wechat_padc.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.wechat_padc.databinding.ActivityCreateProfileBinding
import com.example.wechat_padc.mvp.presenters.CreatProfilePresenterImpl
import com.example.wechat_padc.mvp.view.CreatProfileView
import com.example.wechat_padc.utils.day
import com.example.wechat_padc.utils.yearList

class CreateProfileActivity : BaseActivity(), CreatProfileView {
    private lateinit var binding: ActivityCreateProfileBinding

    private lateinit var mPresenter: CreatProfilePresenterImpl

    private var mGender: String = "other"

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CreateProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpPresenter()
        setUpRadioButtons()
        setUpSpinners()
        setUpListeners()
        mPresenter.onUiReady(this)
    }

    private fun setUpSpinners() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, day)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerDay.adapter = adapter
        binding.spinnerDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val day = parent?.getItemAtPosition(position).toString()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val adapterYear = ArrayAdapter(this, android.R.layout.simple_spinner_item, yearList)
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerYear.adapter = adapterYear

        binding.spinnerYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val year = parent?.getItemAtPosition(position).toString()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun setUpRadioButtons() {
        binding.rbOther.isChecked = true

        binding.rbFemale.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mGender = "Female"
                binding.rbOther.isChecked = false
                binding.rbMale.isChecked = false
            }
        }
        binding.rbMale.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mGender = "Male"
                binding.rbOther.isChecked = false
                binding.rbFemale.isChecked = false
            }
        }

        binding.rbOther.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mGender = "Other"
                binding.rbFemale.isChecked = false
                binding.rbMale.isChecked = false
            }
        }


    }

    private fun setUpListeners() {

//        if (binding.cbAgreeToTermAndService.isChecked) {
//            if (
//                binding.etNameSignUp.text.toString().trim().isNotEmpty() &&
//                binding.edtEmailSignUp.text.toString().trim().isNotEmpty() &&
//                binding.etPasswordSignUp.text.toString().trim().isNotEmpty() &&
//                binding.spinnerDay.selectedItem.toString() != "Day" &&
//                binding.spinnerMonth.selectedItem.toString() != "Month" &&
//                binding.spinnerYear.selectedItem.toString() != "Year"
//            ) {
//                binding.btnSignUpFinish.background =
//                    ContextCompat.getDrawable(this, R.drawable.background_button_log_in)
//
//                binding.btnSignUpFinish.setOnClickListener {
//
//                }
//            } else {
//                binding.btnSignUpFinish.background =
//                    ContextCompat.getDrawable(this, R.drawable.bkg_button_nclick)
//            }
//        } else {
//            binding.btnSignUpFinish.background =
//                ContextCompat.getDrawable(this, R.drawable.bkg_button_nclick)
//
//        }
        binding.btnSignUpFinish.setOnClickListener {
            mPresenter.onTapSignUp(
                email = binding.edtEmailSignUp.text.toString().trim(),
                name = binding.etNameSignUp.text.toString().trim(),
                password = binding.etPasswordSignUp.text.toString().trim(),
                day = binding.spinnerDay.selectedItem.toString(),
                month = binding.spinnerMonth.selectedItem.toString(),
                year = binding.spinnerYear.selectedItem.toString(),
                gender = mGender
            )
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[CreatProfilePresenterImpl::class.java]
        mPresenter.initView(this)

    }

    override fun navigateToLogIn() {
        startActivity(LogInActivity.newIntent(this))
    }

    override fun showError(message: String) {

    }
}