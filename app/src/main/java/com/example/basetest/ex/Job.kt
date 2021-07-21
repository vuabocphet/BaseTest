package com.example.basetest.ex

import android.os.Handler
import android.os.Looper
import java.util.*


fun Timer.schedule(function: () -> Unit, i: Int) {
    schedule(object : TimerTask() {
        override fun run() {
            function.let { it() }
        }
    }, i.toLong())
}

fun runUIThread(function: () -> Unit) = Handler(Looper.myLooper()!!).post { function.let { it() } }
fun runUIThreadDelay(function: () -> Unit, delay: Int) =
    Handler(Looper.myLooper()!!).postDelayed({ function.let { it() } }, delay.toLong())