package com.paypay.view.activity

import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.paypay.R
import com.paypay.base.BaseActivity
import com.paypay.database.entity.Currency
import com.paypay.databinding.ActivityMainBinding
import com.paypay.model.ResponseState
import com.paypay.view.adapter.CurrencyAdapter
import com.paypay.view.adapter.SpinnerAdapter
import com.paypay.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    val viewModel: CurrencyViewModel by viewModels()
    private var binding: ActivityMainBinding? = null
    private var currencyAdapter: CurrencyAdapter? = null
    private var spinnerAdapter: SpinnerAdapter? = null
    private var currencyList: List<Currency> = listOf()
    private lateinit var responseObserver: Observer<ResponseState<List<Currency>>>
    private lateinit var dbObserver: Observer<ResponseState<Unit>>

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(view: View?) {
        binding = DataBindingUtil.bind(view!!)
        binding?.etCurrency?.afterTextChange { 
            val amount = it?.toDouble() ?: 0.0
//            viewModel.
        }

        currencyAdapter = CurrencyAdapter{}
        binding?.rvCurrency?.apply { 
            layoutManager = GridLayoutManager(context, 3)
            adapter = currencyAdapter
        }

        spinnerAdapter = SpinnerAdapter(this)
        binding?.spCurrency?.adapter = spinnerAdapter
        binding?.spCurrency?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    pos: Int,
                    id: Long
                ) {
                    currencyAdapter?.updateSelectedCurrencyRate(currencyList[pos].rate)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        // Database Response observer
        dbObserver  =  Observer {
            when (it) {
                is ResponseState.Loading -> {

                }

                is ResponseState.Success -> {
                }

                is ResponseState.Error -> {
                    Snackbar.make(binding?.root!!, "${it.exception}", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        // Network response observer
        responseObserver  =  Observer {
            when (it) {
                is ResponseState.Loading -> {
                    showLoadingView()
                }

                is ResponseState.Success -> {
                    hideLoadingView()
                    this.currencyList = it.data
                    viewModel.updateCurrencies(currencyList).observe(this, dbObserver)
                    setAdapterData(currencyList)
                }

                is ResponseState.Error -> {
                    hideLoadingView()
                    showErrorView(it.exception)
                }
            }
        }

        viewModel.getCurrencyData().observe(this, responseObserver)
    }

    private fun setAdapterData(data: List<Currency>) {
        currencyAdapter?.setData(data)
        spinnerAdapter?.setDataList(data)
    }

    override fun retry() {
        viewModel.getCurrencyData().observe(this, responseObserver)
    }
}