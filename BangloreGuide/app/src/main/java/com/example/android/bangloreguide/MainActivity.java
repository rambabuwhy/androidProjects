package com.example.android.bangloreguide;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager bangloreViewPager = (ViewPager) findViewById(R.id.viewpager);

        CategoryAdapter bangloreAdapter = new CategoryAdapter(this,getSupportFragmentManager());
        TabLayout bangloreTabs = (TabLayout) findViewById(R.id.tabs);
        bangloreViewPager.setAdapter(bangloreAdapter);
        bangloreTabs.setupWithViewPager(bangloreViewPager);

    }
}
