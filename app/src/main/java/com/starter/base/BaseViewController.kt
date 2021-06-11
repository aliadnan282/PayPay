package com.starter.base

interface BaseViewController {

    fun showMessage(title: String?, message: String?){}

    fun showLoadingView(){}

    fun hideLoadingView(){}

    fun showEmptyView(){}

    fun showEmptyView(message: String?){}

    fun showErrorView(){}

    fun onInvalidUserSession(){}

    fun showConnectionErrorView(){}

    fun showErrorView(message: String?){}

    fun showContent(){}

    fun retry(){}
}