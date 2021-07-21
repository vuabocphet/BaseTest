package com.example.basetest.test

import androidx.activity.viewModels
import com.example.basetest.R
import com.example.basetest.base.ActivityMMVMCompat
import com.example.basetest.dagger.ApplicationComponent
import com.example.basetest.databinding.ActivityMainBinding

class MainActivity : ActivityMMVMCompat<ActivityMainBinding, MainViewModel>() {

    override fun inject(component: ApplicationComponent) {
        component.inject(this)
    }

    override fun layout(): Int {
        return R.layout.activity_main
    }

    override fun viewModel(): MainViewModel {
        val viewModel : MainViewModel by viewModels()
        viewBinding.viewModel = viewModel
        return viewModel
    }

    override fun fullscreen(): Boolean {
        return false
    }

    override fun runUI() {

    }
}
