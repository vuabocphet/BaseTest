package com.example.basetest.test

import android.content.Context
import android.view.Gravity
import com.example.basetest.R
import com.example.basetest.base.BaseDialog
import com.example.basetest.databinding.DailogTestBinding
import com.example.basetest.ex.runUIThreadDelay

class DiaogTest(context: Context) : BaseDialog<DailogTestBinding>(context) {

    override fun layout(): Int {
        return R.layout.dailog_test
    }

    override fun runUI() {
        runUIThreadDelay({
            viewBinding.testText.setText("Hello ABC")
        }, 1500)
    }

    override fun animate(): Int {
        return R.style.AnimatorSlide
    }

    override fun gravity(): Int {
        return Gravity.CENTER
    }
}