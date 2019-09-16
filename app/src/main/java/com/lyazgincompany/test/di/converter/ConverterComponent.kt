package com.lyazgincompany.test.di.converter

import com.lyazgincompany.test.features.converter.presentation.ConverterActivity
import dagger.Subcomponent

@ConverterScope
@Subcomponent(modules = [ConverterModule::class])
interface ConverterComponent {
    fun inject(converterActivity: ConverterActivity)
}
