package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Qingweid on 2016/5/9.
 */
public class WebViewTestActivity extends Activity {

    private WebView webView;
    String url = "https://connect.devicewebmanager.com/oauth/authorize?redirect_uri=https://test.server.c4mi.com/oplink/lockstate/callback.action&stateMTAwMDAyNDUyMF8xMDAwMDIxNDMz&response_type=code&client_id=ce18245088f40f0b083c1e7fed599fec12f43afbadeea07252eec053762ad8ad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_test_layout);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
