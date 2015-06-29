package com.example.root.jsoup_android_001;


import android.graphics.Bitmap;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

import entity.Catalog;
import entity.ProductCatalog;

public class MainActivity extends ActionBarActivity {
    private ProductCatalog productCatalogList;
    private List<Catalog> catalogList;
    private String[] nameCatalog;
    private String[] imageCatalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productCatalogList = (ProductCatalog) getIntent().getSerializableExtra("productCatalogList");
        catalogList = productCatalogList.getCatalogList();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linactiv_main_03);
        LayoutInflater layoutInflater = getLayoutInflater();

        nameCatalog = new String[catalogList.size()];
        imageCatalog = new String[catalogList.size()];
        for (int i = 0; i < catalogList.size(); i++) {
            Catalog catalog = catalogList.get(i);
            nameCatalog[i] = catalog.getName();
            imageCatalog[i] = catalog.getImage();
        }


        for (int i = 0; i < nameCatalog.length; i++) {
            View item = layoutInflater.inflate(R.layout.intent_product_catalog, linearLayout, false);
            TextView tvName = (TextView) item.findViewById(R.id.text_item_product_catalog);
            tvName.setText(nameCatalog[i]);
            linearLayout.addView(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


