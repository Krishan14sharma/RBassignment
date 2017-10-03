package com.kct.rbassignment.di

import android.content.Context
import com.kct.rbassignment.repository.api.Api
import com.kct.rbassignment.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * Created by krishan on 24/09/17.
 */
@Module
class ApiModule {

    val cacheSize: Long = 10 * 1024 * 1024

    @Provides
    fun provideApi(): Api {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(Api::class.java)
    }

    @Provides
    fun provideOkHttpClient(context: Context, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val cache = Cache(File(context.cacheDir, "http-cache"), cacheSize)
        val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
        return client
    }

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }


}