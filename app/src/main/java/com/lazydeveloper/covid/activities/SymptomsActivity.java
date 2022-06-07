package com.lazydeveloper.covid.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import com.lazydeveloper.covid.R;
import com.lazydeveloper.covid.databinding.ActivitySymptomsBinding;
import java.util.Objects;

public class SymptomsActivity extends AppCompatActivity
{
    ActivitySymptomsBinding symptomsBinding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        symptomsBinding = ActivitySymptomsBinding.inflate(getLayoutInflater());
        setContentView(symptomsBinding.getRoot());

        setSupportActionBar(symptomsBinding.toolbar.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle((" "));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //For changing the color of a back button................................
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        symptomsBinding.toolbar.txttoolbar.setText("Learn More");
    }
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}