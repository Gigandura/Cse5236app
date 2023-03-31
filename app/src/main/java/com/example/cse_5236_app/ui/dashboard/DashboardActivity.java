package com.example.cse_5236_app.ui.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cse_5236_app.R;
import com.example.cse_5236_app.model.SearchApi;

public class DashboardActivity extends AppCompatActivity implements OnMovieClickListener{

    SearchView search_view;
    RecyclerView recycler_view_home;
    SearchRecyclerAdapter adapter;
    RequestManager manager;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);
        Log.v("DashboardActivity", "DashboardActivity onCreate");
        search_view = findViewById(R.id.search_view);
        recycler_view_home = findViewById(R.id.recycler_view_home);
        manager = new RequestManager(this);
        dialog = new ProgressDialog(this);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v("DashboardActivity", "DashboardActivity onQueryTextSubmit");
                dialog.setTitle("Searching...");
                dialog.show();
                manager.searchMovies(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }


    private final OnSearchListener listener = new OnSearchListener() {
        @Override
        public void onResponse(SearchApi response) {
            dialog.dismiss();
            if (response == null) {
                Toast.makeText(DashboardActivity.this, "No data", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(DashboardActivity.this, "Error", Toast.LENGTH_SHORT).show();

        }
    };

    private void showResult(SearchApi response) {
        recycler_view_home.setHasFixedSize(true);
        recycler_view_home.setLayoutManager(new GridLayoutManager(DashboardActivity.this, 1));
        Log.v("DashboardActivity", response.toString());
        adapter = new SearchRecyclerAdapter(this, response.getMovieList(), this);
        recycler_view_home.setAdapter(adapter);
    }

    @Override
    public void onMovieClicked(String id) {
        Toast.makeText(DashboardActivity.this, id, Toast.LENGTH_SHORT).show();
    }
}