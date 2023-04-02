package com.example.cse_5236_app.ui.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse_5236_app.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, category, length;
    ImageView image;
    RatingBar rating;

    OnClickMovieListener onClickMovieListener;

    public MovieViewHolder(@NonNull View itemView, OnClickMovieListener onClickMovieListener) {
        super(itemView);


        title = itemView.findViewById(R.id.movie_title);
        category = itemView.findViewById(R.id.movie_category);
        length = itemView.findViewById(R.id.movie_length);
        image = itemView.findViewById(R.id.movie_image);
        rating = itemView.findViewById(R.id.movie_rating);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        onClickMovieListener.onMovieClick(getAdapterPosition());

    }
}
