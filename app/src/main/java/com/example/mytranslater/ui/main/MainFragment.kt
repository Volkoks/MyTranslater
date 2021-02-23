package com.example.mytranslater.ui.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytranslater.R
import com.example.mytranslater.application.MyApp
import com.example.mytranslater.databinding.FragmentMainBinding
import com.example.mytranslater.model.state.AppState
import com.example.mytranslater.ui.adapter.MainFragmentAdapter
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainFragmentViewModel::class.java)
    }
    private var adapter: MainFragmentAdapter? = null
    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        viewModel.subscribe().observe(viewLifecycleOwner, { renderData(it) })
        binding = FragmentMainBinding.bind(view)
        binding?.mbTilSerch?.setOnClickListener {
            viewModel.getData(binding?.tietMainFragment?.text.toString())
        }

    }

    fun renderData(appState: AppState) {
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


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}