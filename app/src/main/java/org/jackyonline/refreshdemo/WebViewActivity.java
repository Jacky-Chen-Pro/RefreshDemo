package org.jackyonline.refreshdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Jacky on 2016/3/13.
 */
public class WebViewActivity extends FragmentActivity implements RefreshLayout.OnRefreshListener,RefreshLayout.OnLoadMoreListener {
    RefreshLayout refreshLayout;
    WebView webView;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0) {
                refreshLayout.refreshComplete();
                webView.loadUrl("https://www.baidu.com/s?ie=utf8&tn=97272809_hao_pg&ch=2&wd=%E6%A9%A1%E6%9C%A8");
            }else{
                refreshLayout.loadMoreComplete();
                webView.loadUrl("http://www.meishij.net/%E7%82%B8%E9%B8%A1%E7%B2%89");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = (WebView) findViewById(R.id.webview);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()  == KeyEvent.KEYCODE_BACK) {
                    if(webView.canGoBack()) {
                        webView.goBack();
                    }else {
                        WebViewActivity.this.finish();
                    }
                }
                return false;
            }
        });

        webView.loadUrl("http://www.baidu.com");

        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode,event);
    }
}
