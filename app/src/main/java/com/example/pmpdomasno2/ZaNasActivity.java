package com.example.pmpdomasno2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toolbar;

public class ZaNasActivity extends AppCompatActivity {

    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tema.setTema(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_za_nas);
        wv= (WebView) findViewById(R.id.wvZaNas);

        CustomWebViewClient wvClient = new CustomWebViewClient(this);
        wv.setWebViewClient(wvClient);
        wv.loadUrl("https://proizvodcounterrazvivac.weebly.com/#");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
                onBackPressed();
                return true;

    }
   @Override
    public void onBackPressed(){
        if (wv.canGoBack()) {
            wv.goBack();
        }else
        {
            this.finish();

        }

    }


}
