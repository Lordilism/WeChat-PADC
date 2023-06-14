package com.example.wechat_padc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.adapters.MomentsAdapter
import com.example.wechat_padc.data.VO.UserVO
import com.example.wechat_padc.databinding.FragmentMeBinding
import com.example.wechat_padc.dialogs.EditUserDialogFragment
import com.example.wechat_padc.dialogs.QrDialogFragment
import com.example.wechat_padc.mvp.presenters.MeFragmentPresenterImpl
import com.example.wechat_padc.mvp.view.MeFragmentView

class MeFragment : Fragment(),MeFragmentView {
    private lateinit var mBookmarkedAdapter: MomentsAdapter
    private var mEditUserDialogFragment: EditUserDialogFragment? = null
    private var mQrDialogFragment: QrDialogFragment? = null

    private lateinit var mPresenter: MeFragmentPresenterImpl

    private var _binding: FragmentMeBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMeBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpPresenter()
        setUpAdapter()
        setUpDialog()
        setUpListeners()

        mPresenter.onUiReady(this)

    }

    private fun setUpListeners() {


        binding.btnEditProfile.setOnClickListener {
            showEditDialog()
        }

        binding.ivMyQrCode.setOnClickListener {
            showQrDialog()
        }
    }

    private fun setUpDialog() {
        mEditUserDialogFragment = EditUserDialogFragment()
        mQrDialogFragment = QrDialogFragment()

        //Need to add next dialog fragment
    }

    private fun setUpPresenter() {
        mPresenter= ViewModelProvider(this).get(MeFragmentPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    private fun setUpAdapter() {
        mBookmarkedAdapter = MomentsAdapter()
        binding.rvBookmarkedMoments.adapter = mBookmarkedAdapter
        binding.rvBookmarkedMoments.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun showEditDialog() {
        mEditUserDialogFragment?.show(parentFragmentManager,"")
    }

    override fun showQrDialog() {
        mQrDialogFragment?.show(parentFragmentManager,"")
    }

    override fun uploadImage() {
        Toast.makeText(requireContext(),"upload Image", Toast.LENGTH_SHORT).show()
    }

    override fun showUserData(userVO: UserVO) {

        Toast.makeText(requireContext(),"${userVO.name}, ${userVO.dateOfBirth}",Toast.LENGTH_SHORT).show()
        binding.tvMyUserName.text = userVO.name.toString()
        binding.tvMyGender.text = userVO.gender.toString()
        binding.tvMyDateOfBirth.text = userVO.dateOfBirth.toString()


    }

    override fun showError(message: String) {

    }


}