package com.starter.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.starter.database.entity.Currency
import com.starter.databinding.ItemCurrencyRateBinding

class CurrencyAdapter(
    private var currencyList: List<Currency>? = listOf(),
    val listener: (Currency) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CurrencyViewHolder(
            ItemCurrencyRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
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

    inner class CurrencyViewHolder(val binding: ItemCurrencyRateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(device : Currency?) {
            binding.currency = device
            binding.executePendingBindings()
        }

    }
}