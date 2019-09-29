package com.lyazgincompany.test.di.converter

import com.lyazgincompany.domain.interactor.GetRatesFlowUseCase
import com.lyazgincompany.domain.repository.IConverterRepository
import com.lyazgincompany.test.data.ConverterRepository
import com.lyazgincompany.test.features.converter.presentation.ConverterPresenter
import com.lyazgincompany.test.features.converter.presentation.IConverterPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

//@ConverterScope
@Module
class ConverterModule {
//    @ConverterScope
//    @Binds
//    abstract fun provideConverterPresenter(converterPresenter: ConverterPresenter): IConverterPresenter

//    @ConverterScope
//    @Binds
//    abstract fun provideConverterRepository(converterRepository: ConverterRepository): IConverterRepository

    @ConverterScope
    @Provides
    fun provideConverterPresenter(getRatesFlowUseCase: GetRatesFlowUseCase): IConverterPresenter{
        return ConverterPresenter(getRatesFlowUseCase)
    }

    @ConverterScope
    @Provides
    fun provideGetRatesFlowUseCase(converterRepository: ConverterRepository): GetRatesFlowUseCase {
        return GetRatesFlowUseCase(converterRepository)
    }
}