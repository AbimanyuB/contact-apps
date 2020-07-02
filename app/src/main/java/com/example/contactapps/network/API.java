package com.example.contactapps.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class API {
    private static Retrofit retrofit;
    private static APIService apiService;
    private static String BASE_URL = "https://simple-contact-crud.herokuapp.com/";

    public APIService getInstance() {
        if(apiService != null) {
            return apiService;
        }

        if(retrofit == null) {
            initializedRetrofit();
        }
        apiService = retrofit.create(APIService.class);
        return apiService;
    }

    public void initializedRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
