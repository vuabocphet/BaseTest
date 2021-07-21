package com.example.basetest.base

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.basetest.application.get
import com.example.basetest.dagger.ApplicationComponent

abstract class ActivityMMVMCompat<T : ViewDataBinding, TT : ViewModel>
    : AppCompatActivity() {

    private var _binding: T? = null
    private var _viewmodel: TT? = null
    private var _component: ApplicationComponent? = null

    protected val viewBinding get() = _binding!!

    protected val viewModel get() = _viewmodel!!

    protected val component get() = _component!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _component = application.get().appComponent
        inject(component)
        _binding = DataBindingUtil.setContentView(this, layout())
        _binding?.lifecycleOwner = this
        _viewmodel = viewModel()
        if (navigationTranSlucent()) {
            StatusBarUtil.transparentFull(this)
        } else {
            if (statusTranSlucent()) {
                StatusBarUtil.statusTranSlucent(this)
            } else {
                if (fullscreen()) {
                    fullScreen()
                }
            }
        }
        runUI()
    }

    override fun onDestroy() {
        _binding = null
        _viewmodel = null
        super.onDestroy()
    }

    private fun fullScreen() {
        val window = window ?: return
        if (Build.VERSION.SDK_INT < 30) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            return
        }
        val controller = window.insetsController
        if (controller != null) {
            controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            controller.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    protected open fun fullscreen(): Boolean {
        return false
    }

    protected open fun statusTranSlucent(): Boolean {
        return false
    }

    protected open fun navigationTranSlucent(): Boolean {
        return false
    }

    protected abstract fun inject(component: ApplicationComponent)
    protected abstract fun layout(): Int
    abstract fun viewModel(): TT
    protected abstract fun runUI()

}