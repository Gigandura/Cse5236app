package com.example.cse_5236_app.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cse_5236_app.ExecutorRunner;
import com.example.cse_5236_app.model.Movie;
import com.example.cse_5236_app.response.MovieSearchResponse;
import com.example.cse_5236_app.util.Permissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    private MutableLiveData<List<Movie>> mMovies;

    private static MovieApiClient instance;

    private MoviesRunnable moviesRunnable;

    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }

    public void searchMoviesApi(String query, int pageNumber) {

        if (moviesRunnable != null) {
            moviesRunnable = null;
        }

        moviesRunnable = new MoviesRunnable(query, pageNumber);


        final Future execRunner = ExecutorRunner.getInstance().networkIO().submit(moviesRunnable);

        ExecutorRunner.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                execRunner.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);



    }

    private class MoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancel;

        public MoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancel = false;
        }

        @Override
        public void run() {

            try {
                Response response = getMovies(query, pageNumber).execute();

                if (cancel) {
                    return;
                }

                if (response.code() == 200) {

                    List<Movie> movies = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());
                    if (pageNumber == 1) {
                        mMovies.postValue(movies);
                    } else {
                        List<Movie> currentMovies = mMovies.getValue();
                        currentMovies.addAll(movies);
                        mMovies.postValue(currentMovies);
                    }

                } else {
                    String error = response.errorBody().string();
                    Log.v("MovieApiClient", "error: " + error);
                    mMovies.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }

        }

        private Call<MovieSearchResponse> getMovies(String query, int pageNumber) {

            return RequestManager.getMovieApi().searchMovie(
                    Permissions.API_KEY,
                    query,
                    pageNumber
            );

        }

        private void cancel() {
            Log.v("MovieApiClient", "Cancelling request");
            cancel = true;
        }
    }
}
