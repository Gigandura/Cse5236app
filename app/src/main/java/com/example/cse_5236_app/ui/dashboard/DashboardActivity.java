package com.example.cse_5236_app.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cse_5236_app.R;
import com.example.cse_5236_app.model.Movie;
import com.example.cse_5236_app.ui.MainActivity;
import com.example.cse_5236_app.ui.details.MovieDetailsActivity;
import com.example.cse_5236_app.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        if (getIntent().hasExtra("userid")) {
            String userid = getIntent().getStringExtra("userid");
            Log.v("DashboardActivity","incoming intent: " + userid);

        } else {
            Log.v("DashboardActivity", "no intents");
        }


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("DashboardActivity", "onDestroy");
        String userid;
        if (getIntent().hasExtra("userid")) {
            userid = getIntent().getStringExtra("userid");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userid", userid);
            startActivity(intent);
        }
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
        if (getIntent().hasExtra("userid")) {
            String userid = getIntent().getStringExtra("userid");
            intent.putExtra("userid", userid);

        }
        startActivity(intent);

    }

    @Override
    public void onCategoryClick(String category) {

    }


}