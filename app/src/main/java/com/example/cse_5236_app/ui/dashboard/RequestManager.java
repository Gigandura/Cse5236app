package com.example.cse_5236_app.ui.dashboard;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.cse_5236_app.model.SearchApi;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://online-movie-database.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void searchMovies(OnSearchListener listener, String movie_name) {
        getMovies getMovies = retrofit.create(RequestManager.getMovies.class);
        Call<SearchApi> call = getMovies.callMovies(movie_name);

        call.enqueue(new Callback<SearchApi>() {
            @Override
            public void onResponse(Call<SearchApi> call, Response<SearchApi> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Couldn't fetch data", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.v("RequestManager", "RequestManager searchMovies onResponse");
                Log.v("RequestManager", response.body().getMovieList().toString());
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchApi> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public interface getMovies {
        @Headers({
                "Accept: application/json",
                "X-RapidAPI-Key: ae14a6780dmsh668943abefeb2aap148d54jsn888616b0b516",
                "X-RapidAPI-Host: online-movie-database.p.rapidapi.com"
        })
        @GET("/title/find")
        Call<SearchApi> callMovies(
                @Query("q") String movie_name
                //@Path("movie_name") String movie_name
        );
    }
}
