package com.paypay.base

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.paypay.R
import com.paypay.extensions.gone
import com.paypay.extensions.invisible
import com.paypay.extensions.visible

abstract class BaseActivity : AppCompatActivity(), BaseLayout, BaseViewController {
    val NO_LAYOUT_SPECIFIED = 0
    protected var emptyView: View? = null
    protected var errorView: View? = null
    protected var loadingView: View? = null
    protected var connectionView: View? = null
    protected var mainContentView: View? = null

    private var mViewStub: ViewStub? = null
    private var loadingViewStub: ViewStub? = null
    private var errorViewStub: ViewStub? = null
    private var emptyViewStub: ViewStub? = null
    private var connectionViewStub: ViewStub? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_layout)

        if (getLayoutId() != NO_LAYOUT_SPECIFIED) {
            mViewStub = findViewById(R.id.base_content)
            mViewStub?.layoutResource = getLayoutId()
            mainContentView = mViewStub?.inflate()
        }

        if (getLoadingLayout() != NO_LAYOUT_SPECIFIED) {
            loadingViewStub = findViewById(R.id.base_loading)
            loadingViewStub?.layoutResource = getLoadingLayout()
        }
        if (getEmptyLayout() != NO_LAYOUT_SPECIFIED) {
            emptyViewStub = findViewById(R.id.base_empty)
            emptyViewStub?.layoutResource = getEmptyLayout()
        }

        if (getErrorLayout() != NO_LAYOUT_SPECIFIED) {
            errorViewStub = findViewById(R.id.base_error)
            errorViewStub?.layoutResource = getErrorLayout()
        }

        if (getConnectionErrorLayout() != NO_LAYOUT_SPECIFIED) {
            connectionViewStub = findViewById(R.id.base_connection)
            connectionViewStub?.layoutResource = getConnectionErrorLayout()
        }

        initView(mainContentView)
    }

    abstract fun initView(view: View?)

    override fun showLoadingView() {
        if (loadingView == null) {
            loadingView = loadingViewStub!!.inflate()
        }
        if (isLoadingHideContent() && mainContentView != null)
            mainContentView?.invisible()
        if (emptyView != null) emptyView?.gone()
        if (errorView != null) errorView?.gone()
        loadingView?.visible()
    }

    override fun hideLoadingView() {
        showContent()
    }
    override fun showEmptyView() {
        if (emptyView == null) {
            emptyView = emptyViewStub!!.inflate()
        }
        if (loadingView != null) loadingView?.gone()
        if (mainContentView != null) mainContentView?.gone()
        emptyView?.visible()

    }

    override fun showContent() {
        if (loadingView != null && loadingView!!.visibility == View.VISIBLE)
            loadingView?.gone()
        if (emptyView != null && emptyView!!.visibility == View.VISIBLE)
            emptyView?.gone()

        if (mainContentView == null) {
            mViewStub = findViewById(R.id.base_content)
            mViewStub?.layoutResource = getLayoutId()
            mainContentView = mViewStub?.inflate()
        }

        mainContentView?.visible()

    }

    override fun showErrorView(message: String?) {
        if (errorView == null) {
            errorView = errorViewStub!!.inflate()
        }
        if (loadingView != null) loadingView?.gone()
        if (emptyView != null) emptyView?.gone()
        if (mainContentView != null) mainContentView?.gone()
        errorView?.visible()
        errorView?.findViewById<TextView>(R.id.tv_error_generic)?.text = message
        errorView?.findViewById<TextView>(R.id.tv_retry)?.setOnClickListener{retry()}

    }

    override fun showConnectionErrorView() {
        if (connectionView == null) {
            connectionView = connectionViewStub!!.inflate()
        }
        if (emptyView != null) emptyView?.gone()
        if (loadingView != null) loadingView?.gone()
        if (mainContentView != null)
            mainContentView?.gone()
        if (errorView != null)
            errorView?.gone()
        connectionView?.visible()

    }

    override fun getLoadingLayout(): Int {
        return R.layout.base_loading
    }

    override fun getEmptyLayout(): Int {
        return R.layout.base_empty
    }

    override fun getErrorLayout(): Int {
        return R.layout.base_error
    }

    override fun getConnectionErrorLayout(): Int {
        return R.layout.base_no_connection
    }

    override fun isLoadingHideContent(): Boolean {
        return false
    }
}