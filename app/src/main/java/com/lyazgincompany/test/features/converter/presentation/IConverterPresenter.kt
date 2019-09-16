package com.lyazgincompany.test.features.converter.presentation

import com.lyazgincompany.test.data.Rate
import com.lyazgincompany.test.utils.ConnectionStateMonitor

interface IConverterPresenter{
    fun onInputChanged(rate: Rate)
    fun onInputValueChanged(rate: Double)
    fun onTryAgain()
    fun onNetworkStateChanged(networkState: ConnectionStateMonitor.NetworkState?)
}
