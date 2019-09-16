package com.lyazgincompany.test.features.converter.presentation

import com.arellomobile.mvp.MvpView
import com.lyazgincompany.test.data.Rate

interface ConverterView : MvpView {
    fun showItems(rates: List<Rate>)
    fun showLoading()
    fun showContent()
    fun showError(message: String)
    fun updateRates(rates: MutableList<Rate>)
    fun updateInput(rate: Rate)

}
