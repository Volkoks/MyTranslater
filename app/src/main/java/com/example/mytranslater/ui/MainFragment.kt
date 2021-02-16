package com.example.mytranslater.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytranslater.R
import com.example.mytranslater.contract.ContractMainFragment
import com.example.mytranslater.databinding.FragmentMainBinding
import com.example.mytranslater.model.state.AppState
import com.example.mytranslater.presenter.MainFragmentPresenter
import com.example.mytranslater.ui.adapter.MainFragmentAdapter


class MainFragment : Fragment(R.layout.fragment_main), ContractMainFragment.View {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val presenter: MainFragmentPresenter<AppState, ContractMainFragment.View> by lazy {
        MainFragmentPresenter()
    }
    private var adapter: MainFragmentAdapter? = null
    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding?.mbTilSerch?.setOnClickListener {
            presenter.getData(binding?.tietMainFragment?.text.toString(), true)
        }

    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Succes -> {
                binding?.progressMainFradment?.visibility = ViewGroup.GONE
                val data = appState.data
                if (adapter == null) {
                    adapter = MainFragmentAdapter()
                    binding?.rvMainFragment?.layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    binding?.rvMainFragment?.adapter = adapter
                    data?.let {
                        adapter?.dataList = it
                    }
                } else {
                    data?.let {
                        adapter?.dataList = it
                    }
                }
            }
            is AppState.Loading -> {
                if (appState.progress == 1) {
                    binding?.progressMainFradment?.visibility = ViewGroup.VISIBLE
                }
            }
            is AppState.Error -> {
                val error = appState.error
                binding?.progressMainFradment?.visibility = ViewGroup.GONE
                Toast.makeText(activity, "Error: $error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView(this)
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}