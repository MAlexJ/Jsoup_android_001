package com.example.root.jsoup_android_001;


import android.app.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import entity.ProductCatalog;
import service.JsoupCatalogService;

public class SplashScreenActivity extends Activity {

    private ProductCatalog productCatalogList;
    private JsoupCatalogService jsoupCatalogService;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        LoadResource loadResource = new LoadResource();
        loadResource.execute();
    }


    class LoadResource extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            jsoupCatalogService = new JsoupCatalogService();
            //timeout -> 1000mc
            jsoupCatalogService.init(1000);
            productCatalogList = jsoupCatalogService.getProductCatalogList();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            intent.putExtra("productCatalogList", productCatalogList);
            startActivity(intent);
            finish();
        }
    }


}
