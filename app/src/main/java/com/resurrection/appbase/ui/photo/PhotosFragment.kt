package com.resurrection.appbase.ui.photo

import android.os.Bundle
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.components.data.DataStoreManager
import com.resurrection.base.core.fragment.BaseFragment
import com.resurrection.base.extensions.toast
import com.resurrection.base.extensions.init
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    private val photoAdapter = PhotoAdapter()

    override fun init(savedInstanceState: Bundle?) {
        initPhotoRecyclerView()
        initPhotoAdapterOnItemClick()
        initPhotosObserver()
        getPhotos()

        binding.button.setOnLongClickListener {
            toast("Button long click")
            true
        }

        photoAdapter.setOnItemLongClickListener {
            toast("item long clicked")
            true
        }


        val dataStore = DataStoreManager(requireContext())

        dataStore.getInt(
            key = "intKey",
            success = {
                requireActivity().runOnUiThread {
                    toast(it)
                }
            }, error = {
                requireActivity().runOnUiThread {
                    toast("hata verdi")
                }
            }
        )

    }

    private fun initPhotoRecyclerView(){
        binding.photoRecyclerView.init(photoAdapter)
    }

    private fun initPhotoAdapterOnItemClick(){
        photoAdapter.setOnItemClickListener {
            toast("item clicked")
        }
    }

    private fun initPhotoAdapterOnItemLongClick(){

    }


    private fun initPhotosObserver(){
        viewModel.photos.observeData(success = {
            it?.let {
                toast(it)
                photoAdapter.addAll(it)

            }
        }, error = {
            toast(it?.message)
        }
        )
    }
    private fun getPhotos(){
        viewModel.getPhotos()
    }
}