package com.paypay.base

interface BaseLayout {
    fun getLayoutId(): Int

    fun getLoadingLayout(): Int

    fun getEmptyLayout(): Int

    fun getErrorLayout(): Int

    fun getConnectionErrorLayout(): Int

    fun isLoadingHideContent(): Boolean
}