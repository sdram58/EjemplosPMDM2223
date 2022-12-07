package com.catata.exampleobservable

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        NumberProvider.startEmitting()
    }
}