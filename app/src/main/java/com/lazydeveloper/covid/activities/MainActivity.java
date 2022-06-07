package com.lazydeveloper.covid.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.lazydeveloper.covid.ApiUtilities.BaseApiService;
import com.lazydeveloper.covid.ApiUtilities.UtilsApi;
import com.lazydeveloper.covid.R;
import com.lazydeveloper.covid.adapter.SpinnerAdapter;
import com.lazydeveloper.covid.databinding.ActivityMainBinding;
import com.lazydeveloper.covid.model.CasesModel;
import com.lazydeveloper.covid.spinner.SpinnerModel;
import com.squareup.picasso.Picasso;

import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    ActivityMainBinding mainBinding;

    List<CasesModel> list = new ArrayList<>();

    ArrayList<SpinnerModel> allList;

    BaseApiService mApiService;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        //Initializing variables....................................................................

        setSupportActionBar(mainBinding.toolbar.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle((" "));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //For changing the color of a back button...................................................
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        mainBinding.toolbar.txttoolbar.setText("Live Update");

        mApiService = UtilsApi.getOthersAPIService();

        checkConnection();

        mainBinding.swiperRefresh.setOnRefreshListener(this::checkConnection);
    }

    private void checkConnection()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
        {
            //we are connected to a network.........................................................
            mainBinding.swiperRefresh.setRefreshing(false);
            getCountryData();
        }
        else
        {
            mainBinding.swiperRefresh.setRefreshing(false);
            Snackbar snackbar = Snackbar.make(mainBinding.mainLayout,"Connection failed. Please check your internet.",Snackbar.LENGTH_LONG);
            mainBinding.mainLayout.setPadding(0, 0, 0, 0);
            snackbar.show();
        }
    }

    private void getCountryData()
    {
        //Custom progressBar........................................................................
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_progress);
        if (dialog.getWindow()!=null)
        {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        dialog.show();

        mApiService.getCovidData().enqueue(new Callback<List<CasesModel>>()
        {
            @Override
            public void onResponse(@NonNull Call<List<CasesModel>> call, @NonNull Response<List<CasesModel>> response) {

                if (response.isSuccessful())
                {
                    assert response.body() != null;
                    list.addAll(response.body());
                    Log.e("Response", list.toString());
                    allList = new ArrayList<>();
                    for (int i = 0; i<list.size(); i++)
                    {
                        SpinnerModel item = new SpinnerModel(list.get(i).getCountry(), list.get(i).getCountryInfo().getFlag());
                        allList.add(item);
                    }

                    Log.e("data",allList.toString());

                    spinner();
                    dialog.dismiss();
                }else
                    Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(@NonNull Call<List<CasesModel>> call, @NonNull Throwable t)
            {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void spinner()
    {
        mainBinding.spinner1.setAdapter(new SpinnerAdapter(this, R.layout.spinner_dropdown, allList));

        mainBinding.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                SpinnerModel spinnerModel = allList.get(position);
                String name = spinnerModel.getName();
                Log.e("SpinnerItem", name);
                //Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
                mainBinding.title.setText(name);
                for (int i = 0; i<list.size(); i++)
                {
                    String stFlag = list.get(i).getCountryInfo().getFlag();
                    String iso = list.get(i).getCountryInfo().getIso3();

                    if(list.get(i).getCountry().equalsIgnoreCase(name))
                    {
                        int confirm = Integer.parseInt(list.get(i).getCases());
                        int newCases = Integer.parseInt(list.get(i).getTodayCases());
                        int acti = Integer.parseInt(list.get(i).getActive());
                        int newActi = Integer.parseInt(list.get(i).getTodayCases());
                        int rec = Integer.parseInt(list.get(i).getRecovered());
                        int newRec = Integer.parseInt(list.get(i).getTodayRecovered());
                        int intDeath = Integer.parseInt(list.get(i).getDeaths());
                        int newDeath = Integer.parseInt(list.get(i).getTodayDeaths());
                        int oneCasePerPeople = Integer.parseInt(list.get(i).getOneCasePerPeople());
                        int critical = Integer.parseInt(list.get(i).getCritical());
                        int oneTestPerPeople = Integer.parseInt(list.get(i).getOneTestPerPeople());
                        float criticalPerOneMillion = (list.get(i).getCriticalPerOneMillion());

                        Picasso.get()
                                .load(stFlag)
                                .error(R.drawable.ic_launcher_background)
                                .into(mainBinding.flag);

                        Log.e("flag", stFlag);

                        mainBinding.tvConfirm.setText(NumberFormat.getInstance().format(confirm));
                        mainBinding.todaysConfirm.setText("(+" + NumberFormat.getInstance().format(newCases) + ")");
                        mainBinding.tvActive.setText(NumberFormat.getInstance().format(acti));
                        mainBinding.TodaysActive.setText("(+" + NumberFormat.getInstance().format(newActi) + ")");
                        mainBinding.tvRecovered.setText(NumberFormat.getInstance().format(rec));
                        mainBinding.todaysRecovered.setText("(+" + NumberFormat.getInstance().format(newRec) + ")");
                        mainBinding.tvDeath.setText(NumberFormat.getInstance().format(intDeath));
                        mainBinding.todaysDeath.setText("(+" + NumberFormat.getInstance().format(newDeath) + ")");
                        mainBinding.tvCases.setText(NumberFormat.getInstance().format(oneCasePerPeople));
                        mainBinding.tvCritical.setText(NumberFormat.getInstance().format(critical));

                        mainBinding.piechart.addPieSlice(new PieModel("Confirm", confirm, Color.parseColor("#FFEB3B")));
                        mainBinding.piechart.addPieSlice(new PieModel("Active", acti, Color.parseColor("#00BCD4")));
                        mainBinding.piechart.addPieSlice(new PieModel("Recovered", rec, Color.parseColor("#4CAF50")));
                        mainBinding.piechart.addPieSlice(new PieModel("Death", intDeath, Color.parseColor("#E63B75")));
                        mainBinding.piechart.addPieSlice(new PieModel("Critical", critical, Color.parseColor("#3F51B5")));

                        mainBinding.piechart.startAnimation();

                        mainBinding.demo.setText(iso);

                        mainBinding.barchart.addBar(new BarModel("A",oneCasePerPeople, 0xFF123456));
                        mainBinding.barchart.addBar(new BarModel("B",critical, 0xFF123456));
                        mainBinding.barchart.addBar(new BarModel("C",oneTestPerPeople, 0xFF123456));
                        mainBinding.barchart.addBar(new BarModel("D",criticalPerOneMillion, 0xFF123456));

                        mainBinding.barchart.startAnimation();
                        setText(list.get(i).getUpdated());

                        Snackbar snackbar = Snackbar.make(mainBinding.mainLayout,"Updated",Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setText(String updated)
    {
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("dd-MM, yyyy");

        long millisec = Long.parseLong(updated);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisec);

        mainBinding.date.setText("Updated at " + format.format(calendar.getTime()));
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}