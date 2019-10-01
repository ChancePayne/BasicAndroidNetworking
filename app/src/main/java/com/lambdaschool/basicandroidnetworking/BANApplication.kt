package com.lambdaschool.basicandroidnetworking

import android.app.Application
import com.facebook.stetho.Stetho

class BANApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}