package com.resurrection.base.core.activity

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VDB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutRes: Int,
    private val viewModelClass: Class<VM>
) : LifecycleActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[viewModelClass] }
    val  binding: VDB by lazy { DataBindingUtil.setContentView(this@BaseActivity, layoutRes) }

}