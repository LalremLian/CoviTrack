package com.lazydeveloper.covid.covid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder {

    @GET("countries")
    Call<List<CasesModel>> getCovidData();
}
