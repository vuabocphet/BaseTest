package com.example.basetest.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDialog<T : ViewDataBinding>(context: Context) : AlertDialog(context) {
    private var _binding: T? = null
    protected val viewBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = context.let {
            LayoutInflater.from(it)
        }.inflate(layout(), null)
        _binding = DataBindingUtil.bind(inflate)
        _binding?.root?.let {
            setContentView(it)
            val window: Window = window!!
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.setBackgroundDrawableResource(android.R.color.transparent)
            window.setGravity(gravity())
            if (animate() != -1) {
                window.setWindowAnimations(animate())
            }
            setCanceledOnTouchOutside(cancelOutSide())
            if (disableBack()) {
                setCancelable(false)
            }
            if (fulldialog()) {
                window.setLayout(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
                )
            } else {
                window.setLayout(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
            }
            runUI()
        }
    }

    @LayoutRes
    protected abstract fun layout(): Int

    protected abstract fun runUI()

    @StyleRes
    open fun animate(): Int {
        return -1
    }

    open fun fulldialog(): Boolean {
        return false
    }

    open fun gravity(): Int {
        return Gravity.BOTTOM
    }

    open fun disableBack(): Boolean {
        return false
    }

    open fun cancelOutSide(): Boolean {
        return true
    }

    open fun showUI() {
        if (isShowing) {
            return
        }
        show()
    }

    open fun showDialogCallback(callback: ((Boolean) -> Unit)? = null) {
        showUI()
    }

    open fun hideUI() {
        if (!isShowing) {
            return
        }
        dismiss()
    }

    open fun removeUI() {
        _binding = null
        cancel()
    }
}