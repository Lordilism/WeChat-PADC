package com.example.wechat_padc.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.wechat_padc.databinding.FragmentQrDialogBinding
import com.example.wechat_padc.mvp.presenters.QrDialogPresenterImpl
import com.example.wechat_padc.mvp.view.QrDialogView


class QrDialogFragment : DialogFragment() ,QrDialogView{

    private var _binding: FragmentQrDialogBinding? = null
    private val binding get() = _binding!!

    //Presenter
    private lateinit var mPresenter: QrDialogPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQrDialogBinding.inflate(inflater,container,false)
        val view  = binding.root
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpPresenter()
        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(QrDialogPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    override fun showError(message: String) {

    }


}