package com.lyazgincompany.test.di.app

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule{

    @AppScope
    @Binds
    abstract fun provideContext(application: Application): Context
}
