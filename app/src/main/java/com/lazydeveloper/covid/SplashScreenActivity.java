package com.lazydeveloper.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity
{
    //Variables............................................
    Animation topAnim, botAnim;
    TextView txTitle, txMoto;
    ImageView imageView;
    Boolean bool;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Animation........................................
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks............................................
        txTitle = findViewById(R.id.title);
        //txMoto = findViewById(R.id.moto);
        imageView = findViewById(R.id.logo);

        imageView.setAnimation(topAnim);
        txTitle.setAnimation(botAnim);
        //txMoto.setAnimation(botAnim);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
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
            }
        },2500);
    }
}