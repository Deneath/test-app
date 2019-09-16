package com.lyazgincompany.test

import com.lyazgincompany.test.api.RatesApi
import com.lyazgincompany.test.api.RatesResponse
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RatesApiTest {
    private lateinit var api: RatesApi
//    private val api = mock(RatesApi::class.java)

    @BeforeEach
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        api = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RatesApi::class.java)
    }

    @Test
    fun `Test that api returns response`() {
        val observer: TestObserver<RatesResponse> =
            api.getRates(AppConstants.DEFAULT_CURRENCY).test()
        observer.assertComplete()
        observer.assertNoErrors()
        observer.assertNoTimeout()
        observer.dispose()
    }
}
