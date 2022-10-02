package com.example.data.di

import com.example.data.api.ApiService
import com.example.domain.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constants.NetWork.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.NetWork.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.NetWork.READ_TIMEOUT, TimeUnit.SECONDS)
            .cookieJar(JavaNetCookieJar(CookieManager(null, CookiePolicy.ACCEPT_ALL)))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private fun getRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(provideGs()))
            .baseUrl(url)
            .client(provideOkHttpClient())
            .build()
    }

    private fun provideGs(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun service(): ApiService {
        return getRetrofit(
            "https://api.github.com/",
        ).create(ApiService::class.java)
    }
}