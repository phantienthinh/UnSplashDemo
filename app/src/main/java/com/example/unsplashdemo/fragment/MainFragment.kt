package com.example.unsplashdemo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unsplashdemo.R
import com.example.unsplashdemo.api.ApiServer
import com.example.unsplashdemo.databinding.FragmentMainBinding
import com.example.unsplashdemo.fragment.factory.MainFragmentViewModelFactory
import com.example.unsplashdemo.fragment.viewmodel.MainFragmentViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment(), View.OnClickListener {
    lateinit var mainFragmentBinding: FragmentMainBinding
    lateinit var mainViewModel: MainFragmentViewModel
    lateinit var mainListAdapter: MainListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragmentBinding.mainFragmentDetailBt.setOnClickListener(this)
        setupViewModel()
        setupList()
        setupView()

//        Thread {
//            val response = ApiServer.getApiServer()!!.getListImage(1, 30)
//            var list = response
//            Log.e("TAG", "onViewCreated: " + list?.size)
//        }.start()

    }

    private fun setupViewModel() {
        mainViewModel =
            ViewModelProvider(
                this,
                MainFragmentViewModelFactory(ApiServer.getApiServer()!!)
            )[MainFragmentViewModel::class.java]
    }


    private fun setupView() {
        lifecycleScope.launch {
            mainViewModel.listData.collect {
                Log.e("TAG", "setupView:  ")
                mainListAdapter.submitData(it)
            }
            lifecycleScope.launch {
                mainListAdapter.loadStateFlow.collectLatest { loadstate ->
                    val loading = loadstate.refresh is LoadState.Loading
                    val error = loadstate.refresh is LoadState.Error
                    val notloading = loadstate.refresh is LoadState.NotLoading
                    Log.e("TAG", "loading: $loading")
                    Log.e("TAG", "setupView: $error")
                    Log.e("TAG", "setupView: $notloading")
                }
            }
        }
    }

    private fun setupList() {
        mainListAdapter = MainListAdapter()
        mainFragmentBinding.mainFragmentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainListAdapter
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            mainFragmentBinding.mainFragmentDetailBt -> {
                findNavController().navigate(R.id.action_fragment_main_to_detailFragment)
            }
        }
    }
}