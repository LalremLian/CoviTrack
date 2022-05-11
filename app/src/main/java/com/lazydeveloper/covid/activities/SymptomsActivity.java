package com.lazydeveloper.covid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.lazydeveloper.covid.R;

import java.util.Objects;

public class SymptomsActivity extends AppCompatActivity
{
    //For Navigation Drawer.....................................................
    Toolbar toolbar;
    TextView txttoolbar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        //Hooks.................................................................
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txttoolbar = (TextView) findViewById(R.id.txttoolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle((" "));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //For changing the color of a back button................................
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        txttoolbar.setText("Learn More");
    }
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}