package com.example.cse_5236_app.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.cse_5236_app.R;
import com.example.cse_5236_app.model.Movie;
import com.example.cse_5236_app.request.RequestManager;
import com.example.cse_5236_app.response.MovieSearchResponse;
import com.example.cse_5236_app.util.Permissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    Button btn;

    private DashboardViewModel dashboardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);
        btn = findViewById(R.id.button);


        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        ObserveAnyChange();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMovieApi("Fast", 1);
            }
        });



    }

    private void ObserveAnyChange() {

        dashboardViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                if (movies != null) {
                    for (Movie movie : movies) {
                        Log.v("DashboardActivity", "onChanged: " + movie.getTitle());
                    }
                }

            }
        });

    }

    private void searchMovieApi(String query, int pageNumber) {
        dashboardViewModel.searchMovieApi(query, pageNumber);
    }





    /*private void GetRetrofitResponse() {
        MovieApi movieApi = RequestManager.getMovieApi();

        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(
                Permissions.API_KEY,
                "Avenger",
                1);

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    Log.v("DashboardActivity", "Response: " + response.body().toString());
                    List<Movie> movies = new ArrayList<>(response.body().getMovies());

                    for (Movie movie : movies) {
                        Log.v("DashboardActivity", "Title: " + movie.getTitle());
                    }
                } else {
                    try {
                        Log.v("DashboardActivity", "error" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }

    private void GetRetrofitResponseByID() {
        MovieApi movieApi = RequestManager.getMovieApi();

        Call<Movie> responseCall = movieApi.getMovie(
                343611,
                Permissions.API_KEY);

        responseCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.code() == 200) {
                    Movie movie = response.body();
                    Log.v("DashboardActivity", "Movie id response: " + movie.getTitle());
                } else {
                    try {
                        Log.v("DashboardActivity", "error" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }


        });
    } */


}