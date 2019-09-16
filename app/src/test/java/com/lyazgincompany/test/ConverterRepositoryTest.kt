package com.lyazgincompany.test

import com.lyazgincompany.test.api.RatesApi
import com.lyazgincompany.test.features.converter.ConverterRepository
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConverterRepositoryTest {
    lateinit var api: RatesApi
    private lateinit var repository: ConverterRepository


    @BeforeEach
    fun setup() {
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

        repository = ConverterRepository(api)

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `Test that subscription returns no errors and emits values`() {
        val testObserver = repository.getRatesFlowObservable()
            .take(2)
            .test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(2)

        testObserver.dispose()
    }
}
