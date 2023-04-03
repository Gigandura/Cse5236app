package com.example.cse_5236_app.ui.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cse_5236_app.R;
import com.example.cse_5236_app.model.Movie;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MovieDetailsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ImageView imageViewDetails;
    private TextView titleDetails, descDetails;
    private RatingBar ratingDetails;

    private String userid;

    private Movie movie;

    private Button addToDatabaseBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        imageViewDetails = findViewById(R.id.imageView_details);
        titleDetails = findViewById(R.id.textView_title_details);
        descDetails = findViewById(R.id.textView_desc_details);
        ratingDetails = findViewById(R.id.ratingBar_details);
        addToDatabaseBtn = findViewById(R.id.button_add_details);

        GetDataFromIntent();

        addToDatabaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference newRef = mDatabase.child("users").child(userid).child("myList").push();
                mDatabase.child("users")
                        .child(userid)
                        .child("myList")
                        .child(newRef.getKey())
                        .setValue(movie)
                        .addOnSuccessListener(task -> {
                            Toast.makeText(getApplicationContext(), "Successfully Added to List", Toast.LENGTH_SHORT).show();
                        });

            }
        });



    }

    private void GetDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            movie = getIntent().getParcelableExtra("movie");
            Log.v("MovieDetailsActivity","incoming intent: " + movie.getMovie_overview());

            titleDetails.setText(movie.getTitle());
            descDetails.setText(movie.getMovie_overview());
            ratingDetails.setRating((movie.getVote_average())/2);

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path())
                    .into(imageViewDetails);
        }

        if (getIntent().hasExtra("userid")) {
            userid = getIntent().getStringExtra("userid");
            Log.v("MovieDetailsActivity","incoming intent: " + userid);

        }
    }
}