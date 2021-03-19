package com.example.mytranslater.ui.history

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytranslater.R
import com.example.mytranslater.application.MyApp
import com.example.mytranslater.databinding.HistoryFragmentBinding
import com.example.model.entites.Word
import com.example.model.state.AppState
import com.example.mytranslater.ui.adapter.IItemClickListener
import com.example.mytranslater.ui.adapter.MainFragmentAdapter
import com.example.mytranslater.ui.screen_word.WordFragment
import javax.inject.Inject

class HistoryFragment : Fragment(R.layout.history_fragment) {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private var adapter: MainFragmentAdapter? = null
    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HistoryViewModel::class.java)
    }
    private var binding: HistoryFragmentBinding? = null

    private val itemClick: IItemClickListener =
        object : IItemClickListener {
            override fun onClick(data: Word) {
                data.text?.let { WordFragment.newInstance(it, "", "") }?.let {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(
                            R.id.fragment_container,
                            it
                        )
                        ?.addToBackStack("WordFragment")
                        ?.commit()
                }
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        binding = HistoryFragmentBinding.bind(view)
        viewModel.subscribe().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData("")

    }

    fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Succes -> {

                val data = appState.data
                if (adapter == null) {
                    adapter = MainFragmentAdapter(itemClick)
                    binding?.rvHistoryFragment?.layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    binding?.rvHistoryFragment?.adapter = adapter
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

                }
            }
            is AppState.Error -> {
                val error = appState.error
                Toast.makeText(activity, "Error: $error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        adapter = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData("")
    }


}