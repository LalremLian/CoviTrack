package com.lazydeveloper.covid.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.lazydeveloper.covid.R;
import com.lazydeveloper.covid.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    ActivityDashboardBinding dashBinding;

    //Variable......................................................................................
    Boolean backPress = false; //This one is for backButton.........................................

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(dashBinding.getRoot());

        //Custom ToolBar............................................................................
        dashBinding.navView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,dashBinding.drawerLayout,dashBinding.toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        dashBinding.drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();

        //Without NavigationView, drawerMenu navigation won't work..................................
        dashBinding.navView.setNavigationItemSelectedListener(this);

        dashBinding.liveCard.setOnClickListener(v ->
        {
            Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
            startActivity(intent);
        });
        dashBinding.learnCard.setOnClickListener(v ->
        {
            Intent intent = new Intent(DashboardActivity.this,SymptomsActivity.class);
            startActivity(intent);
        });
    }
    //For back button...............................................................................
    @Override
    public void onBackPressed()
    {
        if(dashBinding.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            dashBinding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(backPress)
        {
            super.onBackPressed();
        }
        else
        {
            Snackbar snackbar = Snackbar.make(dashBinding.dashboardLayout,"Press again to Exit.",Snackbar.LENGTH_SHORT);
            dashBinding.dashboardLayout.setPadding(0, 0, 0, 0);
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
        dashBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}