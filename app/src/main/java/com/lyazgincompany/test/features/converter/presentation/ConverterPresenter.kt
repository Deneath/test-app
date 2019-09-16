package com.lyazgincompany.test.features.converter.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.lyazgincompany.test.data.Rate
import com.lyazgincompany.test.features.converter.IConverterRepository
import com.lyazgincompany.test.utils.ConnectionStateMonitor
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@InjectViewState
class ConverterPresenter @Inject constructor(
    private val repository: IConverterRepository
) :
    MvpPresenter<ConverterView>(),
    IConverterPresenter {

    private var ratesSubscription: Disposable? = null
    private var lastItems = mutableListOf<Rate>()

    override fun attachView(view: ConverterView?) {
        super.attachView(view)

        viewState.showLoading()
        subscribeOnRates()
    }

    override fun detachView(view: ConverterView?) {
        super.detachView(view)

        unsubscribeFromRates()
    }

    override fun onInputChanged(rate: Rate) {
        repository.currentRate = rate
        viewState.updateInput(rate)
    }

    override fun onInputValueChanged(rate: Double) {
        repository.currentRate.rate = rate
        updateRates(lastItems.map { it.copy() }.toMutableList())
    }

    override fun onTryAgain() {
        subscribeOnRates()
    }

    private fun subscribeOnRates() {
        ratesSubscription = repository.getRatesFlowObservable()
            .doOnNext {
                lastItems = it.map { item -> item.copy() }.toMutableList()
            }
            .subscribe({ rates ->
                updateRates(rates.toMutableList())
            }, {
                if (ratesSubscription?.isDisposed == false) {
                    ratesSubscription?.dispose()
                }
            })
    }

    private fun updateRates(rates: MutableList<Rate>) {
        val multipliedRates = multiplyRates(rates, repository.currentRate.rate)

        multipliedRates.add(0, repository.currentRate)
        viewState.showContent()
        viewState.updateRates(multipliedRates.toMutableList())
    }

    private fun multiplyRates(rates: MutableList<Rate>, multiplier: Double): MutableList<Rate> {
        return rates.toMutableList().onEach { it.rate = it.rate * multiplier }
    }

    private fun unsubscribeFromRates() {
        ratesSubscription?.dispose()
    }

    override fun onNetworkStateChanged(networkState: ConnectionStateMonitor.NetworkState?) {
        when (networkState) {
            ConnectionStateMonitor.NetworkState.Connected -> {
                if (ratesSubscription?.isDisposed == true) {
                    subscribeOnRates()
                }
            }
            ConnectionStateMonitor.NetworkState.Lost -> {
                if (ratesSubscription?.isDisposed == false) {
                    unsubscribeFromRates()
                }
            }
        }
    }
}
