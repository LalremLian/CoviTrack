package com.lazydeveloper.covid.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import com.lazydeveloper.covid.R;
import com.lazydeveloper.covid.databinding.ActivityAboutMeBinding;
import java.util.Objects;

public class AboutMeActivity extends AppCompatActivity {

    ActivityAboutMeBinding aboutMeBinding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutMeBinding = ActivityAboutMeBinding.inflate(getLayoutInflater());
        setContentView(aboutMeBinding.getRoot());

        setSupportActionBar(aboutMeBinding.toolbar.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle((" "));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //For changing the color of a back button...................................................
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        aboutMeBinding.toolbar.txttoolbar.setText("Profile");

        aboutMeBinding.imgGithub.setOnClickListener(v ->
        {
            Intent i = new Intent(android.content.Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://github.com/LalremLian/"));
            startActivity(i);
        });
        aboutMeBinding.imgLinkedin.setOnClickListener(v ->
        {
            Intent i = new Intent(android.content.Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://www.linkedin.com/in/lalrem-lian-b-tlung-63b9691b4/"));
            startActivity(i);
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
//                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
