package com.example.mytranslater.application

import android.app.Application
import com.example.mytranslater.di.AppComponent
import com.example.mytranslater.di.DaggerAppComponent

class MyApp : Application() {

    companion object {
        lateinit var instance: MyApp
    }

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().app(this).build()
    }
}