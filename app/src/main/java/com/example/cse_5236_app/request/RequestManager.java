package com.example.cse_5236_app.request;

import com.example.cse_5236_app.ui.dashboard.MovieApi;
import com.example.cse_5236_app.util.Permissions;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Permissions.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi() {
        return movieApi;
    }

}
