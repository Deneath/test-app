package com.lyazgincompany.test.di

import com.lyazgincompany.test.data.BASE_URL
import com.lyazgincompany.test.data.api.RatesApi
import com.lyazgincompany.test.di.app.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//@AppScope
@Module
class NetworkModule {
    @AppScope
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @AppScope
    @Provides
    fun provideRatesApi(retrofit: Retrofit): RatesApi {
        return retrofit.create(RatesApi::class.java)
    }
}
