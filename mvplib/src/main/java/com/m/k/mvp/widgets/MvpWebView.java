package com.m.k.mvp.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.m.k.mvp.utils.MvpUtils;


public class MvpWebView extends WebView {
    public MvpWebView(Context context) {
        super(context);
        initWebView(this);
    }

    public MvpWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebView(this);
    }

    public MvpWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initWebView(this);
    }


    private void initWebView(WebView webView) {

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webSettings.setAllowFileAccess(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setSupportZoom(false);

        webSettings.setUseWideViewPort(true);

        webSettings.setDisplayZoomControls(false);

        webSettings.setBuiltInZoomControls(false);

        webSettings.setSupportMultipleWindows(true);

        webSettings.setAppCacheEnabled(true);

        webSettings.setDomStorageEnabled(true);

        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);

        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webSettings.setLoadWithOverviewMode(true);

        webSettings.setGeolocationEnabled(true);

        if(MvpUtils.hasJellyBeanMR1()){
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }


        webView.setWebChromeClient(new WebChromeClient() {

        });



        /**
         * 一. 什么时候调用:
         *
         *     当新的 url 即将被加载的时候，也就是用户点击了 Webview 内容里面的一个超链接的时候会触发该方法的调用；
         *
         * 二. 为什么要实现：
         *
         *     我们需要新的链接 url 就加载在当前 WebView 里面，或者我们需要自己的应用程序去处理响应该链接；
         *     大概意思就是：
         *
         *     提供给当前应用一个机会去单独处理 WebView 即将加载的一个新链接。如果 WebViewClient 没有设置，也就是没有调用 WebView 的 setWebViewClient 方法，
         * 那默认就会让用户去选择一个浏览器应用，比如系统浏览器去加载这个新的链接了(并不是所有的手机或者版本都会调用系统浏览器，有的手机上不调用)。那如果 WebViewClient 是设置了的（用webview 自己处理），
         * 返回 True 代表当前应用已经处理了这个新链接了，不需要你 WebView 再去加载这个链接了，当然了，返回 False 的话 WebView 就会横插一脚，去加载这个新的链接。
         *
         *
         * 注意：某些链接点击时，即使返回 true，也能加载，但是有的链接 返回true 点击了就没反应，所以，为了让webview 里面发生点击能在当前webview 加载，那么return false. 默认也是return false
         *
         * @param view
         * @param url
         * @return
         */

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }


        });
    }


}
