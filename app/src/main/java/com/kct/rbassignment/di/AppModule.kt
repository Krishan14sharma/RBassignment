package com.kct.rbassignment.di

import android.content.Context
import com.kct.rbassignment.BaseApp
import com.kct.rbassignment.repository.api.Api
import com.kct.rbassignment.repository.api.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by krishan on 25/09/17.
 */

@Module
class ApplicationModule(private val context: BaseApp) {

    @Singleton
    @Provides
    internal fun provideApplicationContext(): Context {
        return context.applicationContext
    }

    @Singleton
    @Provides
    internal fun provideRemoteRepository(api: Api): RemoteRepository {
        return RemoteRepository(api)
    }

}
