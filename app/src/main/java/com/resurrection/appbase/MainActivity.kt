package com.resurrection.appbase

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.resurrection.appbase.databinding.ActivityMainBinding
import com.resurrection.base.core.activity.CoreActivity
import com.resurrection.base.extensions.delegated.viewdatabinding.dataBinding
import com.resurrection.base.extensions.delegated.viewmodel.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : CoreActivity(R.layout.activity_main) {

    val binding by dataBinding<ActivityMainBinding>()
    val viewModel by viewModel<MainActivityViewModel>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun init(savedInstanceState: Bundle?) {
    }
}