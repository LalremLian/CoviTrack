package com.lazydeveloper.covid.ApiUtilities;

import com.lazydeveloper.covid.model.CasesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BaseApiService {
    @GET("countries")
    Call<List<CasesModel>> getCovidData();
}
