package com.example.marcin.mywords;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiClient {
    @Headers({"Accept: application/json",
            })
    @GET("{word}/definitions")
    Call<Example> GetExampleFor (@Path("word") String word);
}
