package com.lyazgincompany.test.features.converter.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.lyazgincompany.test.R
import com.lyazgincompany.test.TestApp
import com.lyazgincompany.domain.Rate
import com.lyazgincompany.test.utils.ConnectionStateMonitor
import kotlinx.android.synthetic.main.activity_converter.*
import javax.inject.Inject


class ConverterActivity : MvpAppCompatActivity(),
    ConverterView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ConverterPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private var adapter: CurrencyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        TestApp.injectionManager().getConverterComponent().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        setupViews()
    }

    private fun setupViews() {
        tryAgainButton.setOnClickListener { presenter.onTryAgain() }

        adapter = CurrencyAdapter(
            presenter::onInputChanged,
            presenter::onInputValueChanged
        )

        converterRecyclerView.layoutManager = LinearLayoutManager(this)
        converterRecyclerView.adapter = adapter
        converterRecyclerView.itemAnimator = null

        val csm = ConnectionStateMonitor()
        csm.networkState.observe(this, Observer { presenter.onNetworkStateChanged(it) })
        csm.enable(this
        )
    }

    override fun updateInput(rate: Rate) {
        converterRecyclerView.post {
            converterRecyclerView.scrollToPosition(0)
            adapter?.updateInput(rate)
        }
    }

    override fun showItems(rates: List<Rate>) {
        showContent()
        converterRecyclerView.post {
            adapter?.updateItems(rates)
        }
    }

    override fun updateRates(rates: MutableList<Rate>) {
        converterRecyclerView.post {
            adapter?.updateItems(rates)
        }
    }

    override fun showLoading() {
        errorStateLayout.visibility = View.GONE
        converterProgressBar.visibility = View.VISIBLE
        converterRecyclerView.visibility = View.GONE
    }

    override fun showContent() {
        errorStateLayout.visibility = View.GONE
        converterProgressBar.visibility = View.GONE
        converterRecyclerView.visibility = View.VISIBLE
    }

    override fun showError(message: String) {
        errorMessageTextView.text = message

        errorStateLayout.visibility = View.VISIBLE
        converterProgressBar.visibility = View.GONE
        converterRecyclerView.visibility = View.GONE
    }
}