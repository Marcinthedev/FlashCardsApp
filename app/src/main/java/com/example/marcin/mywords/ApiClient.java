package com.example.marcin.mywords;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiClient {
    @Headers({"Accept: application/json",
            "app_id: ae33532f",
            "app_key: 39761616836d300839cb5205b7ac3220"})
    @GET("{word}/definitions")
    Call<Example> GetExampleFor (@Path("word") String word);
}
