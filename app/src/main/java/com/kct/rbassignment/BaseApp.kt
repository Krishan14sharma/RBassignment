package com.kct.rbassignment

import android.app.Application
import com.kct.rbassignment.di.ApiModule
import com.kct.rbassignment.di.AppComponent
import com.kct.rbassignment.di.ApplicationModule
import com.kct.rbassignment.di.DaggerAppComponent

/**
 * Created by krishan on 25/09/17.
 */
class BaseApp : Application() {

    lateinit var appComponent: AppComponent private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .apiModule(ApiModule())
                .applicationModule(ApplicationModule(this))
                .build()
        appComponent.inject(this)
    }

}