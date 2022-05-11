package com.lazydeveloper.covid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.lazydeveloper.covid.R;

public class AboutMeActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txttoolbar;
    ImageView imgGit, imgLinkedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txttoolbar);

        imgGit = findViewById(R.id.img_github);
        imgLinkedin = findViewById(R.id.img_linkedin);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle((" "));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //For changing the color of a back button...................................................
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        txttoolbar.setText("Profile");

        imgGit.setOnClickListener(v ->
        {
            Intent i = new Intent(android.content.Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://github.com/LalremLian/"));
            startActivity(i);
        });
        imgLinkedin.setOnClickListener(v ->
        {
            Intent i = new Intent(android.content.Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://www.linkedin.com/in/lalrem-lian-b-tlung-63b9691b4/"));
            startActivity(i);
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
//                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
