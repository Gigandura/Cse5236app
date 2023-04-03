package com.example.cse_5236_app.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cse_5236_app.R;
import com.example.cse_5236_app.model.Movie;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Movie> mMovies;
    private OnClickMovieListener onClickMovieListener;

    public MovieRecyclerView(OnClickMovieListener onClickMovieListener) {
        this.onClickMovieListener = onClickMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(v, onClickMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MovieViewHolder) holder).title.setText(mMovies.get(position).getTitle());
        // change to caregory vvvvv
        ((MovieViewHolder) holder).release_date.setText("Released: " + mMovies.get(position).getRelease_date());
        ((MovieViewHolder) holder).length.setText("Language: " +  mMovies.get(position).getOriginal_language());
        // Note: rating is out of 10 in api, we want out of 5
        ((MovieViewHolder) holder).rating.setRating((mMovies.get(position).getVote_average())/2);

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + mMovies.get(position).getPoster_path())
                .into(((MovieViewHolder)holder).image);
    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;

    }

    public void setmMovies(List<Movie> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }

    public Movie getSelectedMovie(int pos) {
        if ((mMovies != null) && (mMovies.size() > 0) ) {
            return mMovies.get(pos);
        }

        return null;
    }
}
