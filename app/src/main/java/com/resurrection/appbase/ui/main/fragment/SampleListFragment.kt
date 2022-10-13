package com.resurrection.appbase.ui.main.fragment

import android.os.Bundle
import android.view.View
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentSampleListBinding
import com.resurrection.appbase.ui.main.adapter.SamplesRecyclerViewAdapter
import com.resurrection.appbase.ui.main.viewmodel.SampleListViewModel
import com.resurrection.base.core.fragment.BaseFragment
import com.resurrection.base.extensions.init
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleListFragment : BaseFragment<FragmentSampleListBinding,SampleListViewModel>(
    R.layout.fragment_sample_list,SampleListViewModel::class.java
) {
    private val sampleListAdapter = SamplesRecyclerViewAdapter()

    override fun init(view: View, savedInstanceState: Bundle?) {
        initViews()
        iniObservers()
        getSampleList()
    }

    private fun initViews() = binding.samplesRecyclerView.init(sampleListAdapter)

    private fun iniObservers(){
        viewModel.samplesLiveData.observeData(
            success = {
                loadingIndicator.hide()
                it?.let { samples ->
                    sampleListAdapter.addAll(samples)
                }

            }, loading = {
                loadingIndicator.show()
            }, error = {
                loadingIndicator.hide()
            }
        )
    }

    private fun getSampleList() = viewModel.getSampleList()
}