package com.example.cse_5236_app.ui.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cse_5236_app.R;
import com.example.cse_5236_app.model.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView imageViewDetails;
    private TextView titleDetails, descDetails;
    private RatingBar ratingDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        imageViewDetails = findViewById(R.id.imageView_details);
        titleDetails = findViewById(R.id.textView_title_details);
        descDetails = findViewById(R.id.textView_desc_details);
        ratingDetails = findViewById(R.id.ratingBar_details);

        GetDataFromIntent();

    }

    private void GetDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            Movie movie = getIntent().getParcelableExtra("movie");
            Log.v("MovieDetailsActivity","incoming intent: " + movie.getMovie_overview());

            titleDetails.setText(movie.getTitle());
            descDetails.setText(movie.getMovie_overview());
            ratingDetails.setRating((movie.getVote_average())/2);

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path())
                    .into(imageViewDetails);
        }
    }
}