package com.lyazgincompany.test.di

import android.app.Application
import com.lyazgincompany.test.di.app.AppComponent
import com.lyazgincompany.test.di.app.DaggerAppComponent
import com.lyazgincompany.test.di.converter.ConverterComponent

class InjectionManager(private val application: Application){

    private var appComponent: AppComponent? = null

    private var converterComponent: ConverterComponent? = null

    private fun getAppComponent(): AppComponent {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                .application(application)
                .build()
        }

        return appComponent as AppComponent
    }

    fun getConverterComponent(): ConverterComponent {
        if (converterComponent == null) {
            converterComponent = getAppComponent().getConverterComponent()
        }

        return converterComponent as ConverterComponent
    }

    fun releaseConverterComponent() {
        converterComponent = null
    }
}