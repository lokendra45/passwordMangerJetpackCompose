package com.loken

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PasswordManagerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}