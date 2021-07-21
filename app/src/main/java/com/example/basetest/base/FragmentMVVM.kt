package com.example.basetest.base

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.basetest.application.get
import com.example.basetest.dagger.ApplicationComponent

abstract class FragmentMVVM<T : ViewDataBinding, TT : ViewModel> : Fragment() {

    private var _binding: T? = null
    private var _viewmodel: TT? = null
    private var _component: ApplicationComponent? = null

    protected val viewBinding get() = _binding!!

    protected val viewModel get() = _viewmodel!!

    protected val component get() = _component!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _component = requireActivity().application.get().appComponent
        inject(component)
        _binding = DataBindingUtil.inflate(inflater, layout(),container,false)
        _binding?.lifecycleOwner = this
        _viewmodel = viewModel()
        runUI()
        return viewBinding.root
    }



    protected abstract fun inject(component: ApplicationComponent)
    protected abstract fun layout(): Int
    abstract fun viewModel(): TT
    protected abstract fun runUI()
}