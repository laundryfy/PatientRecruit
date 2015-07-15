package com.oneworldinc.android.patientrecruit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class TrialInfoActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial_info);

        View view = getLayoutInflater().inflate(R.layout.action_bar_view, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView titleView = (TextView) view.findViewById(R.id.title_text);
        titleView.setText("Clinical Trial Recruitment Center");
        ImageView logo = (ImageView) view.findViewById(R.id.logo);
        logo.setImageResource(R.drawable.ic_transparent);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setCustomView(view);
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
            actionBar.setDisplayShowCustomEnabled(true);
        }

        actionBar.setHomeButtonEnabled(true);
//        webView = (WebView) findViewById(R.id.webView_trialInfo);
//        webView.loadUrl("file:///android_asset/TrialInfo.html");

//Web page link open inside the Application
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true); // enable javascript
        final Activity activity = this;
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });
        webView.loadUrl("file:///android_asset/TrialInfo.html");
        setContentView(webView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                startActivity(new Intent(this, TrialDetailActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
