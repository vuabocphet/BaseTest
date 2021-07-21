package com.example.basetest.application

import android.app.Application

fun Application.get(): BaseApplication {
    return this as BaseApplication
}