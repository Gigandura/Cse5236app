package com.example.cse_5236_app.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cse_5236_app.model.Movie;
import com.example.cse_5236_app.repo.MovieRepository;

import java.util.List;

public class DashboardViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public DashboardViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieRepository.getMovies();
    }
}