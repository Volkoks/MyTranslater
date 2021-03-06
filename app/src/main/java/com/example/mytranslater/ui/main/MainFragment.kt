package com.example.mytranslater.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytranslater.R
import com.example.mytranslater.application.MyApp
import com.example.mytranslater.databinding.FragmentMainBinding
import com.example.model.entites.Word
import com.example.model.state.AppState
import com.example.mytranslater.ui.adapter.IItemClickListener
import com.example.mytranslater.ui.adapter.MainFragmentAdapter
import com.example.mytranslater.ui.history.HistoryFragment
import com.example.mytranslater.ui.screen_word.WordFragment
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
    private val itemClick: IItemClickListener =
        object : IItemClickListener {
            override fun onClick(data: Word) {
                data.text?.let {
                    data.meanings?.get(0)?.let { it1 ->
                        it1.translation?.translation?.let { it2 ->
                            it1.imageUrl?.let { it3 ->
                                WordFragment.newInstance(
                                    it,
                                    it2,
                                    it3
                                )
                            }
                        }
                    }
                }
                    ?.let {
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
        setHasOptionsMenu(true)

        viewModel.subscribe().observe(viewLifecycleOwner, { renderData(it) })
        binding = FragmentMainBinding.bind(view)

        binding?.mbTilSerch?.setOnClickListener {
            viewModel.getData(binding?.tietMainFragment?.text.toString())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_menu_history -> {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, HistoryFragment.newInstance())
                    ?.addToBackStack("historyFragment")
                    ?.commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Succes -> {
                binding?.progressMainFradment?.visibility = ViewGroup.GONE
                val data = appState.data
                if (adapter == null) {
                    adapter = MainFragmentAdapter(itemClick)
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


    override fun onPause() {
        super.onPause()
        adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}