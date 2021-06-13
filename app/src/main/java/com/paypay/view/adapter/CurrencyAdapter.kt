package com.paypay.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paypay.database.entity.Currency
import com.paypay.databinding.ItemCurrencyRateBinding

class CurrencyAdapter(
    private var currencyList: List<Currency>? = listOf(),
    val listener: (Currency) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedAmount = 1.0
    private var selectedCurrencyExchangeRate = 1.0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CurrencyViewHolder(
            ItemCurrencyRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun updateSelectedCurrencyRate(rate: Double) {
        this.selectedCurrencyExchangeRate = rate
        notifyDataSetChanged()
    }

    fun convert(amountToConvert: Double) {
        this.selectedAmount = amountToConvert
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CurrencyViewHolder)
            holder.bind(currencyList?.get(position))
    }

    override fun getItemCount(): Int {
        return currencyList?.size ?: 0
    }

    fun setData(deviceCatalogueResponse: List<Currency>?) {
        this.currencyList = deviceCatalogueResponse
        notifyDataSetChanged()
    }

    inner class CurrencyViewHolder(private val binding: ItemCurrencyRateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Currency?) {
            item?.countryFlag = binding.root.context.resources.getFlagResource(item?.code!!)
            binding.currency = item
            binding.tvRate.text = String.format("%.2f", calculateExchangeAmount(item))
            binding.executePendingBindings()
        }
    }

    private fun calculateExchangeAmount(item: Currency?): Float? {
        return item?.rate?.times(selectedAmount)?.div(selectedCurrencyExchangeRate)?.toFloat()
    }
}