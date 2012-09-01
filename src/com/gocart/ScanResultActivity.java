package com.gocart;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
//import android.widget.*;

public class ScanResultActivity extends Activity {
	
//    private TextView mBarcodeText;
    WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanresultviaweb);
        
        String product = getIntent().getExtras().getString("contents"); 
        StringBuffer urlBuffer =  new StringBuffer();
        urlBuffer.append("http://ec2-50-19-68-173.compute-1.amazonaws.com/product/");
        urlBuffer.append(product); urlBuffer.append("/");

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(urlBuffer.toString());
        mWebView.setWebViewClient(new HelloWebViewClient());
        mWebView.setVerticalScrollBarEnabled(false);
        


//        mBarcodeText = (TextView) findViewById(R.id.edit_text);         
//        mBarcodeText.setText(product);
    }
    
    @Override
    public void onBackPressed() {
        // It's expensive, if running turn it off.
        super.onBackPressed();
        finish();
    }
    
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    
}