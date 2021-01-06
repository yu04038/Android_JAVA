package com.example.webviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "https://www.naver.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url); //특정 url주소를 틀기
        webView.setWebChromeClient(new WebChromeClient()); //크롬환경에 맞게 조정
        webView.setWebViewClient(new WebViewClientClass());
    }

    //뒤로가기를 눌렀을 때 원래화면으로 돌아가기
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //뒤로가기를 누르고, webview가 뒤로 갈수 있는 상황일때
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        //현재 페이지의 url을 읽어올 수 있다.
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}