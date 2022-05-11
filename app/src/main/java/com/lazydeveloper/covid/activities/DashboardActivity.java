package com.lazydeveloper.covid.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.lazydeveloper.covid.R;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    //Variables.....................................................................................
    Boolean backPress = false; //This one is for backButton.........................................

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    LinearLayout layout;

    CardView live;
    CardView learnMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Initializing Variables....................................................................
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        layout = findViewById(R.id.dashboard_layout);
        live = findViewById(R.id.liveCard);
        learnMore = findViewById(R.id.learnCard);

        //Custom ToolBar............................................................................
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();

        //Without NavigationView, drawerMenu navigation won't work..................................
        navigationView.setNavigationItemSelectedListener(this);

        live.setOnClickListener(v ->
        {
            Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
            startActivity(intent);
        });
        learnMore.setOnClickListener(v ->
        {
            Intent intent = new Intent(DashboardActivity.this,SymptomsActivity.class);
            startActivity(intent);
        });
    }
    //For back button...............................................................................
    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(backPress)
        {
            super.onBackPressed();
        }
        else
        {
            Snackbar snackbar = Snackbar.make(layout,"Press again to Exit.",Snackbar.LENGTH_SHORT);
            layout.setPadding(0, 0, 0, 0);
            snackbar.show();
            backPress = true;
            new Handler().postDelayed(() -> backPress = false,2000);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.home:
                Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                Intent intent1 = new Intent(DashboardActivity.this, AboutMeActivity.class);
                startActivity(intent1);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}