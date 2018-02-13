package com.example.karan.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.example.karan.myapplication.image_essentials.Pager;
import com.example.karan.myapplication.R;

//This class is defined to store Fragments FamousPlaces and Description as swipeable tabs
public class CitiesDescription extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    String cityName,cityDetail;
    private TabLayout tabLayout;
    private String[] head;
    private ViewPager viewPager;
    private int city_pos;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_description);
        SharedPreferences prefs = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        cityName = prefs.getString("city","a");
        cityDetail =prefs.getString("city1","d");

        head=getResources().getStringArray(R.array.city);

        TypedArray rainbow = getResources().obtainTypedArray(R.array.rainbow);
        TypedArray rainbow1 = getResources().obtainTypedArray(R.array.rainbow1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(cityName);

        //Data is sent to Fragments using bundles
        city_pos = getIntent().getIntExtra("position",0);
        Bundle bundle = new Bundle();
        bundle.putInt("cityIndex", city_pos);
        bundle.putString("desc",cityDetail);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("City details"));
        tabLayout.addTab(tabLayout.newTab().setText("Sites"));
        tabLayout.setFadingEdgeLength(2);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount(), bundle);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);
        int pos = head.length;

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        for (int i = 0; i <=pos ; i++) {
            if (city_pos == i) {
                toolbar.setTitle("\n" + head[i]);
                toolbar.setBackground(rainbow.getDrawable(i));
                tabLayout.setBackground(rainbow.getDrawable(i));
                window.setStatusBarColor(rainbow1.getColor(i,0));
            }
        }
    }
    public int getMyData() {
        return city_pos;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

