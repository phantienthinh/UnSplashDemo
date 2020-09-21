package com.example.unsplashdemo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.unsplashdemo.R
import com.example.unsplashdemo.ReposLoadStateAdapter
import com.example.unsplashdemo.Utils
import com.example.unsplashdemo.api.ApiServer
import com.example.unsplashdemo.api.objUnSplash.UnSplash
import com.example.unsplashdemo.databinding.FragmentMainBinding
import com.example.unsplashdemo.fragment.factory.MainFragmentViewModelFactory
import com.example.unsplashdemo.fragment.repository.UnsplashRepository
import com.example.unsplashdemo.fragment.viewmodel.MainFragmentViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class MainFragment : Fragment(), onItemListener {
    lateinit var mainFragmentBinding: FragmentMainBinding
    lateinit var mainViewModel: MainFragmentViewModel
    private var mainListAdapter: MainListAdapter = MainListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setupView()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        setupList()
        lifecycleScope.launch {
            mainListAdapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { mainFragmentBinding.mainFragmentRecyclerView.scrollToPosition(0) }
        }
        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(TAG, "onViewCreated: ")
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            MainFragmentViewModelFactory(UnsplashRepository(ApiServer.getApiServer()!!))
        )[MainFragmentViewModel::class.java]
    }


    private fun setupView() {
        mainViewModel.getListDataImageUnsplash().observe(this, Observer {
            this.lifecycleScope.launch {
                mainListAdapter.submitData(it)
            }
        })

    }

    private fun setupList() {
//        mainListAdapter = MainListAdapter(this)
//        mainFragmentBinding.mainFragmentRecyclerView.adapter = mainListAdapter
        mainFragmentBinding.mainFragmentRecyclerView.adapter =
            mainListAdapter.withLoadStateHeaderAndFooter(
                header = ReposLoadStateAdapter { mainListAdapter.retry() },
                footer = ReposLoadStateAdapter { mainListAdapter.retry() }
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "onDestroyView: ")
    }


    override fun onClickItem(unSplash: UnSplash) {
        val bundle = Bundle()
        bundle.putSerializable(Utils.VALUE_DATA, unSplash)
        findNavController().navigate(R.id.action_fragment_main_to_detailFragment, bundle)
    }

    companion object {
        private const val TAG = "MainFragment"
    }
}