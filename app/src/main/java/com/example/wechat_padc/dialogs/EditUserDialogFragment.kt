package com.example.wechat_padc.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.wechat_padc.databinding.FragmentEditUserDialogBinding
import com.example.wechat_padc.mvp.presenters.EditDialogPresenterImpl
import com.example.wechat_padc.mvp.view.EditDialogView

class EditUserDialogFragment : DialogFragment(),EditDialogView {

    private var _binding: FragmentEditUserDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var mPresenter:EditDialogPresenterImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditUserDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpPresenter()
        setUpListeners()
        mPresenter.onUiReady(this)
    }

    private fun setUpListeners() {
        binding.btnCancel.setOnClickListener {
            cancelEdit()
        }

        binding.btnSave.setOnClickListener {
            saveUserInfo()
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(EditDialogPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    override fun saveUserInfo() {
        Toast.makeText(requireContext(),"Saved", Toast.LENGTH_SHORT).show()
        dismiss()
    }

    override fun cancelEdit() {
        this.dismiss()
    }

    override fun showError(message: String) {

    }


}