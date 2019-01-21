package com.example.marcin.mywords;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceApi {

    static final String API_URL="https://od-api.oxforddictionaries.com:443/api/v1/entries/en/";
    static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create());
    static Retrofit retrofit = builder.build();

    public static ApiClient createServiceApi(){
        return retrofit.create(ApiClient.class);
    }


}
