package com.example.cse_5236_app.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cse_5236_app.R;
import com.example.cse_5236_app.model.Movie;
import com.example.cse_5236_app.ui.details.MovieDetailsActivity;

import java.util.List;

public class DashboardActivity extends AppCompatActivity implements OnClickMovieListener{

    private RecyclerView recyclerView;

    private MovieRecyclerView adapter;
    private DashboardViewModel dashboardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);
        recyclerView = findViewById(R.id.recyclerView);

        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        ConfigureRecyclerView();

        ObserveAnyChange();

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dashboardViewModel.searchMovieApi(
                        query,
                        1
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
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

                        adapter.setmMovies(movies);
                    }
                }

            }
        });

    }

    private void ConfigureRecyclerView() {
        adapter = new MovieRecyclerView(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onMovieClick(int pos) {

        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie", adapter.getSelectedMovie(pos));
        startActivity(intent);

    }

    @Override
    public void onCategoryClick(String category) {

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