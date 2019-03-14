package com.darcos.julie.mynews.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;

import com.darcos.julie.mynews.R;

public class WebViewActivity extends AppCompatActivity {
    private Toolbar toolbarweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        this.configureToolBar();

        WebView webView = findViewById(R.id.activity_main_webview);
        webView.loadUrl(getIntent().getStringExtra("url"));
    }

    private void configureToolBar() {
        this.toolbarweb = (Toolbar) findViewById(R.id.activity_webview);
        setSupportActionBar(toolbarweb);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_white_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getIntent().getStringExtra("title"));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}
