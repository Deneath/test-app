package com.lyazgincompany.test.di.converter

import com.lyazgincompany.test.features.converter.ConverterRepository
import com.lyazgincompany.test.features.converter.IConverterRepository
import com.lyazgincompany.test.features.converter.presentation.ConverterPresenter
import com.lyazgincompany.test.features.converter.presentation.IConverterPresenter
import dagger.Binds
import dagger.Module

@ConverterScope
@Module
abstract class ConverterModule{
    @ConverterScope
    @Binds
    abstract fun provideConverterPresenter(converterPresenter: ConverterPresenter): IConverterPresenter

    @ConverterScope
    @Binds
    abstract fun provideConverterRepository(converterRepository: ConverterRepository): IConverterRepository
}