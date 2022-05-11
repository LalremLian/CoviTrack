package com.lazydeveloper.covid.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.lazydeveloper.covid.ApiUtilities.BaseApiService;
import com.lazydeveloper.covid.ApiUtilities.UtilsApi;
import com.lazydeveloper.covid.R;
import com.lazydeveloper.covid.adapter.SpinnerAdapter;
import com.lazydeveloper.covid.model.CasesModel;
import com.lazydeveloper.covid.spinner.SpinnerModel;
import com.squareup.picasso.Picasso;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
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
     ImageView flag;
    TextView tconfirm, active, recovered, death, tvDate,tvTitle, countryName, tvCases, tvCritical;
    TextView todaysCon, todaysAct, todaysRecov, todaysDeath;
    PieChart pieChart;
    LinearLayout layout;
    BarChart mBarChart;
    SwipeRefreshLayout swipeRefreshLayout;

    //For Navigation Drawer.........................................................................
    Toolbar toolbar;
    TextView txttoolbar;

    List<CasesModel> list = new ArrayList<>();

    ArrayList<SpinnerModel> allList;
    Spinner spinner;

    BaseApiService mApiService;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing variables....................................................................
        tconfirm = findViewById(R.id.tvConfirm);
        active = findViewById(R.id.tvActive);
        recovered = findViewById(R.id.tvRecovered);
        death = findViewById(R.id.tvDeath);
        todaysCon = findViewById(R.id.todaysConfirm);
        todaysAct = findViewById(R.id.TodaysActive);
        todaysRecov = findViewById(R.id.todaysRecovered);
        todaysDeath = findViewById(R.id.todaysDeath);
        todaysDeath = findViewById(R.id.todaysDeath);
        layout = findViewById(R.id.mainLayout);
        pieChart = findViewById(R.id.piechart);
        tvDate = findViewById(R.id.date);
        spinner = findViewById(R.id.spinner1);
        tvTitle = findViewById(R.id.title);
        countryName = findViewById(R.id.tvCountryName);
        tvCases = findViewById(R.id.tvCases);
        tvCritical = findViewById(R.id.tvCritical);
        flag = findViewById(R.id.flag);
        mBarChart = findViewById(R.id.barchart);
        swipeRefreshLayout = findViewById(R.id.swiper_refresh);

        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txttoolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle((" "));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //For changing the color of a back button...................................................
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        txttoolbar.setText("Live Update");

        mApiService = UtilsApi.getOthersAPIService();

        checkConnection();

        swipeRefreshLayout.setOnRefreshListener(this::checkConnection);
    }

    private void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            swipeRefreshLayout.setRefreshing(false);
            getCountryData();
        }
        else
        {
            swipeRefreshLayout.setRefreshing(false);
            Snackbar snackbar = Snackbar.make(layout,"Connection failed. Please check your internet.",Snackbar.LENGTH_LONG);
            layout.setPadding(0, 0, 0, 0);
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

        mApiService.getCovidData().enqueue(new Callback<List<CasesModel>>() {
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
            public void onFailure(@NonNull Call<List<CasesModel>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void spinner()
    {
        spinner = findViewById(R.id.spinner1);
        spinner.setAdapter(new SpinnerAdapter(this, R.layout.spinner_dropdown, allList));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                SpinnerModel spinnerModel = allList.get(position);
                String name = spinnerModel.getName();
                Log.e("SpinnerItem", name);
                //Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
                tvTitle.setText(name);
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
                                .into(flag);

                        Log.e("flag", stFlag);

                        tconfirm.setText(NumberFormat.getInstance().format(confirm));
                        todaysCon.setText("(+" + NumberFormat.getInstance().format(newCases) + ")");
                        active.setText(NumberFormat.getInstance().format(acti));
                        todaysAct.setText("(+" + NumberFormat.getInstance().format(newActi) + ")");
                        recovered.setText(NumberFormat.getInstance().format(rec));
                        todaysRecov.setText("(+" + NumberFormat.getInstance().format(newRec) + ")");
                        death.setText(NumberFormat.getInstance().format(intDeath));
                        todaysDeath.setText("(+" + NumberFormat.getInstance().format(newDeath) + ")");
                        tvCases.setText(NumberFormat.getInstance().format(oneCasePerPeople));
                        tvCritical.setText(NumberFormat.getInstance().format(critical));

                        pieChart.addPieSlice(new PieModel("Confirm", confirm, Color.parseColor("#FFEB3B")));
                        pieChart.addPieSlice(new PieModel("Active", acti, Color.parseColor("#00BCD4")));
                        pieChart.addPieSlice(new PieModel("Recovered", rec, Color.parseColor("#4CAF50")));
                        pieChart.addPieSlice(new PieModel("Death", intDeath, Color.parseColor("#E63B75")));
                        pieChart.addPieSlice(new PieModel("Critical", critical, Color.parseColor("#3F51B5")));

                        pieChart.startAnimation();

                        TextView tvDemo = findViewById(R.id.demo);
                        tvDemo.setText(iso);

                        mBarChart.addBar(new BarModel("A",oneCasePerPeople, 0xFF123456));
                        mBarChart.addBar(new BarModel("B",critical, 0xFF123456));
                        mBarChart.addBar(new BarModel("C",oneTestPerPeople, 0xFF123456));
                        mBarChart.addBar(new BarModel("D",criticalPerOneMillion, 0xFF123456));

                        mBarChart.startAnimation();
                        setText(list.get(i).getUpdated());

                        Snackbar snackbar = Snackbar.make(layout,"Updated",Snackbar.LENGTH_SHORT);
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

        tvDate.setText("Updated at " + format.format(calendar.getTime()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}