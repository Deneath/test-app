package com.lyazgincompany.test

import android.app.Application
import com.lyazgincompany.test.di.InjectionManager

class TestApp : Application(){

    companion object {
        private lateinit var injectionManager: InjectionManager

        fun injectionManager(): InjectionManager = injectionManager
    }

    override fun onCreate() {
        super.onCreate()

        injectionManager = InjectionManager(this)
    }
}
