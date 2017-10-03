package com.kct.rbassignment.di

import com.kct.rbassignment.BaseApp
import com.kct.rbassignment.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by krishan on 25/09/17.
 */

@Singleton
@Component(modules = arrayOf(ApiModule::class, ApplicationModule::class))
interface AppComponent {
    fun inject(baseApp: BaseApp)
    fun inject(baseApp: MainActivity)
}