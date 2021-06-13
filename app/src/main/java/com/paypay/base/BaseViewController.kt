package com.paypay.base

interface BaseViewController {

    fun showMessage(title: String?, message: String?){}

    fun showLoadingView(){}

    fun hideLoadingView(){}

    fun showEmptyView(){}

    fun showEmptyView(message: String?){}

    fun onInvalidUserSession(){}

    fun showConnectionErrorView(){}

    fun showErrorView(message: String?= null){}

    fun showContent(){}

    fun retry(){}
}