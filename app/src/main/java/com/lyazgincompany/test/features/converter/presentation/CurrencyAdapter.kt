package com.lyazgincompany.test.features.converter.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lyazgincompany.test.R
import com.lyazgincompany.test.addSimpleTextChangeListener
import com.lyazgincompany.test.data.Rate
import com.lyazgincompany.test.formatToDouble
import com.lyazgincompany.test.formatToString
import kotlinx.android.synthetic.main.list_item_currency.view.*
import java.util.*

class CurrencyAdapter(
    private val onInputChanged: (Rate) -> Unit,
    private val onInputValueChanged: (Double) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<Rate> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_currency, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.apply {
            setOnClickListener {
                if (holder.adapterPosition != 0) {
                    onInputChanged(items[holder.adapterPosition])
                    val value = rateEditText.text.toString().formatToDouble()
                    onInputValueChanged(value)
                }
            }

            currencyTextView.text = item.currency
            rateEditText.setText(item.rate.formatToString())

            rateEditText.apply {
                if (position == 0) {
                    requestFocus()
                }
                setOnFocusChangeListener { _, isFocused ->
                    if (isFocused && holder.adapterPosition != 0) {
                        onInputChanged(items[holder.adapterPosition])
                        val value = rateEditText.text.toString().formatToDouble()
                        onInputValueChanged(value)
                    }
                }

                addSimpleTextChangeListener {
                    if (holder.adapterPosition == 0) {
                        if (it.isEmpty()) {
                            onInputValueChanged(0.0)
                        } else {
                            onInputValueChanged(it.formatToDouble())
                        }
                    }
                }
            }
        }
    }

    fun updateItems(items: List<Rate>) {
        if (this.items.isEmpty()) {
            this.items.addAll(items)
            notifyDataSetChanged()
        } else {
            items.forEachIndexed { index, rate ->
                if (index == 0) return@forEachIndexed
                this.items.firstOrNull { it.currency == rate.currency }?.rate = rate.rate
                notifyItemChanged(index)
            }
        }
    }

    fun updateInput(rate: Rate) {
        val selectedIndex = items.indexOfFirst { it.currency == rate.currency }

        if (selectedIndex != 0) {
            Collections.swap(items, 0, selectedIndex)

            notifyItemChanged(0)
            notifyItemChanged(selectedIndex)
        }
    }
}