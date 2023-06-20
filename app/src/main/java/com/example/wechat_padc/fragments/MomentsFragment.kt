package com.example.wechat_padc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.activities.NewMomentActivity
import com.example.wechat_padc.adapters.MomentsAdapter
import com.example.wechat_padc.data.VO.MomentsVO
import com.example.wechat_padc.databinding.FragmentMomentsBinding
import com.example.wechat_padc.mvp.presenters.MomentsPresenterImpl
import com.example.wechat_padc.mvp.view.MomentsView


class MomentsFragment : Fragment(), MomentsView {
    //Presenter
    private lateinit var mPresenter: MomentsPresenterImpl

    private lateinit var mMomentsAdapter: MomentsAdapter
    private var _binding: FragmentMomentsBinding? = null



    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMomentsBinding.inflate(inflater, container, false)
        val view = binding.root

        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpPresenter()
        setUpMomentsRecyclerView()

        setUpListeners()

        mPresenter.onUiReady(this)

    }

    private fun setUpListeners() {
        binding.btnNewMoment.setOnClickListener {
            mPresenter.onTapCreateMoments()
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(MomentsPresenterImpl::class.java)
        mPresenter.initView(this)

    }

    private fun setUpMomentsRecyclerView() {
        mMomentsAdapter = MomentsAdapter()
        binding.rvMoment.adapter = mMomentsAdapter
        binding.rvMoment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


    }

    override fun navigateToCreateMoment() {
        startActivity(NewMomentActivity.newIntent(requireContext()))
    }

    override fun showFeed(list: List<MomentsVO>) {
        mMomentsAdapter.setNewData(list)


    }

    override fun showError(message: String) {



    }


}