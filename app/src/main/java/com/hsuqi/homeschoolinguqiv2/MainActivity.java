package com.hsuqi.homeschoolinguqiv2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements AdvancedWebView.Listener{

    private AdvancedWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        webView.setListener(this,this);
        webView.setMixedContentAllowed(false);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    if (url.contains("intent:")) {
                        startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME));
                        return true;
                    } else if (!url.contains("zoom.us") && !url.contains("meet.google")) {
                        return false;
                    } else {
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                        return true;
                    }
                } catch (Exception unused) {
                    return false;
                }
            }
        });
        webView.loadUrl("https://app.hsuqi.com");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK && this.webView.canGoBack()){
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        webView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (!webView.onBackPressed()) { return; }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        webView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        webView.onPause();
        super.onPause();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }
}
