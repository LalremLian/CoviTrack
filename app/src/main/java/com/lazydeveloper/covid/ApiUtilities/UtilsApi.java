package com.lazydeveloper.covid.ApiUtilities;

public class UtilsApi {
    public static final String BASE_URL_API = "https://corona.lmao.ninja/v2/"; //live

    public static BaseApiService getOthersAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
