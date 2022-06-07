package com.lazydeveloper.covid.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.lazydeveloper.covid.R;
import com.lazydeveloper.covid.databinding.ActivitySplashScreenBinding;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity
{
    ActivitySplashScreenBinding splashBinding;

    //Variables.....................................................................................
    Animation topAnim, botAnim;
    Boolean bool;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        splashBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(splashBinding.getRoot());

        //Animation.................................................................................
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        splashBinding.logo.setAnimation(topAnim);
        splashBinding.title.setAnimation(botAnim);

        new Handler().postDelayed(() ->
        {
            SharedPreferences sh = getSharedPreferences("OnBoarding",0);
            bool = sh.getBoolean("Boolean",false);
            if (bool)
            {
                Intent intent1 = new Intent(SplashScreenActivity.this, DashboardActivity.class);
                startActivity(intent1);
                finish();
            }else
            {
                Intent intent2 = new Intent(SplashScreenActivity.this, OnBoardingActivity.class);
                startActivity(intent2);
                finish();
            }
        },2500);
    }
}