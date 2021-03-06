package com.lazydeveloper.covid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lazydeveloper.covid.R;
import com.lazydeveloper.covid.adapter.AdapterPager;

public class OnBoardingActivity extends AppCompatActivity {

    //Variables.....................................................................................
    ViewPager mSLideViewPager;
    LinearLayout mDotLayout;
    Button backbtn, nextbtn, skipbtn;

    TextView[] dots;
    AdapterPager adapterPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        //Initializing Variables....................................................................
        backbtn = findViewById(R.id.back);
        nextbtn = findViewById(R.id.next);
        skipbtn = findViewById(R.id.skip);

        mSLideViewPager = findViewById(R.id.viewPager1);
        mDotLayout = findViewById(R.id.indicator_layput);

        SharedPreferences sh = getSharedPreferences("OnBoarding",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();

        myEdit.putBoolean("Boolean",true);
        myEdit.apply();

        adapterPager = new AdapterPager(this);
        mSLideViewPager.setAdapter(adapterPager);
        setUpindicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);

        backbtn.setOnClickListener(v ->
        {
            if (getitem(0) > 0)
            {
                mSLideViewPager.setCurrentItem(getitem(-1),true);
            }
        });
        nextbtn.setOnClickListener(v ->
        {
            if (getitem(0) < 2)
                mSLideViewPager.setCurrentItem(getitem(1),true);
            else {
                Intent i = new Intent(OnBoardingActivity.this,DashboardActivity.class);
                startActivity(i);
                finish();
            }
        });
        skipbtn.setOnClickListener(v ->
        {
            Intent i = new Intent(OnBoardingActivity.this,DashboardActivity.class);
            startActivity(i);
            finish();
        });
    }

    private void setUpindicator(int position)
    {
        dots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);

            if (position > 0) {

                backbtn.setVisibility(View.VISIBLE);

            } else
                backbtn.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getitem(int i)
    {
        return mSLideViewPager.getCurrentItem() + i;
    }
}