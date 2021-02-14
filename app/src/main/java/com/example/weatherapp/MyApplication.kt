package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.data.api.implementations.WeatherServiceImpl
import com.example.weatherapp.data.api.interfaces.IWeatherService
import com.example.weatherapp.helpers.BASEURL
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.google.gson.Gson
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(singletonModule,implementationModule, viewModelModule)
        }
    }
    private val singletonModule= module {
        single{
            /*val cacheSize: Long = 10* 1024 *1024 //10mb
            val mCache = Cache(cacheDir,cacheSize)*/
            OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE })
                .build()
        }
        single{ Gson() }
        single{
            Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
        }
    }
    private val implementationModule= module {
        factory<IWeatherService>{WeatherServiceImpl(get())}
    }
    private val viewModelModule= module {
        viewModel { WeatherViewModel(get())}
    }
}