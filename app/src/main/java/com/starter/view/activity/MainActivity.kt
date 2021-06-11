package com.starter.view.activity

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.starter.R
import com.starter.base.BaseActivity
import com.starter.database.entity.Currency
import com.starter.databinding.ActivityMainBinding
import com.starter.extensions.afterTextChange
import com.starter.model.ResponseState
import com.starter.view.adapter.CurrencyAdapter
import com.starter.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    val viewModel: MainViewModel by viewModels()
    private var binding: ActivityMainBinding? = null
    private var currencyAdapter: CurrencyAdapter? = null
    private lateinit var responseObserver: Observer<ResponseState<List<Currency>>>

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
        responseObserver  =  Observer {
            when (it) {
                is ResponseState.Loading -> {
                    showLoadingView()
                }

                is ResponseState.Success -> {
                    Toast.makeText(this, "${it.data.size}", Toast.LENGTH_SHORT).show()
                    hideLoadingView()
                    setAdapterData(it.data)
                    viewModel.updateCurrencies(it.data)
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
    }

    override fun retry() {
        viewModel.getCurrencyData().observe(this, responseObserver)
    }
}