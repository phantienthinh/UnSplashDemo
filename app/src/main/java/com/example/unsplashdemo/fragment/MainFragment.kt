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
import com.example.unsplashdemo.MainSearchAdapter
import com.example.unsplashdemo.R
import com.example.unsplashdemo.ReposLoadStateAdapter
import com.example.unsplashdemo.Utils
import com.example.unsplashdemo.api.ApiServer
import com.example.unsplashdemo.databinding.FragmentMainBinding
import com.example.unsplashdemo.fragment.factory.MainFragmentViewModelFactory
import com.example.unsplashdemo.fragment.repository.UnsplashRepository
import com.example.unsplashdemo.fragment.viewmodel.MainFragmentViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class MainFragment : Fragment(), onItemListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    lateinit var mainFragmentBinding: FragmentMainBinding
    lateinit var mainViewModel: MainFragmentViewModel
    private var mainListAdapter: MainListAdapter = MainListAdapter(this)
    private var mainSearchListAdapter: MainSearchAdapter = MainSearchAdapter(this)
    private var job: Job? = null

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
//                .collect { mainFragmentBinding.mainFragmentRecyclerView.scrollToPosition(0) }
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
            job = this.lifecycleScope.launch {
                mainListAdapter.submitData(it)
            }
        })

    }

    private fun setupList() {
        mainFragmentBinding.mainFragmentRecyclerView.adapter =
            mainListAdapter.withLoadStateHeaderAndFooter(
                header = ReposLoadStateAdapter { mainListAdapter.retry() },
                footer = ReposLoadStateAdapter { mainListAdapter.retry() }
            )
        mainFragmentBinding.mainFragmentSearchView.setOnQueryTextListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "onDestroyView: ")
    }


    override fun onClickItem(url: String) {
        val bundle = Bundle()
        bundle.putSerializable(Utils.VALUE_DATA, url)
        findNavController().navigate(R.id.action_fragment_main_to_detailFragment, bundle)
    }

    companion object {
        private const val TAG = "MainFragment"
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val b = newText != null && newText.isEmpty()
        job?.cancel()
        mainFragmentBinding.mainFragmentRecyclerView.adapter =
            mainSearchListAdapter.withLoadStateHeaderAndFooter(
                header = ReposLoadStateAdapter { mainListAdapter.retry() },
                footer = ReposLoadStateAdapter { mainListAdapter.retry() }
            )
        val searchUnSplashImage =
            mainViewModel.searchUnSplashImage(if (b) "beautiful" else newText!!)
        searchUnSplashImage?.observe(this, Observer {
            job = this.viewLifecycleOwner.lifecycleScope.launch {
                mainSearchListAdapter.submitData(it)
            }
        })
        return false
    }


}