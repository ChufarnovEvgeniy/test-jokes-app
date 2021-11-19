package com.github.chufarnovevgeniy.testjokesapp.ui.api_info

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class ApiInfoWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        view.loadUrl(request.url.toString())
        return true
    }
}