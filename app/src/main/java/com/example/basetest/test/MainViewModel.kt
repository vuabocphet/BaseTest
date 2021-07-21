package com.example.basetest.test

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basetest.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

open class MainViewModel @Inject constructor() : BaseViewModel() {

    var visibility: MutableLiveData<Boolean> = MutableLiveData(true)
    var test: MutableLiveData<String> = MutableLiveData("0")

    open fun onClickViews(view: View) {
        viewModelScope.launch {
            val dialogTest = DiaogTest(view.context)
            dialogTest.showUI()
        }
    }

}