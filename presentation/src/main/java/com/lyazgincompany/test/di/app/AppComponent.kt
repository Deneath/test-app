package com.lyazgincompany.test.di.app

import android.app.Application
import com.lyazgincompany.test.di.NetworkModule
import com.lyazgincompany.test.di.converter.ConverterComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun getConverterComponent(): ConverterComponent
}