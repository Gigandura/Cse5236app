package com.example.cse_5236_app.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Movie implements Parcelable {

    public Movie(String title, String poster_path, String release_date, int movie_id, float vote_average, String movie_overview, String original_language) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.overview = movie_overview;
        this.original_language = original_language;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
        vote_average = in.readFloat();
        overview = in.readString();
        original_language = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeInt(movie_id);
        parcel.writeFloat(vote_average);
        parcel.writeString(overview);
        parcel.writeString(original_language);

    }



    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getMovie_overview() {
        return overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    private String title;
    private String poster_path;
    private String release_date;
    private int movie_id;
    private float vote_average;
    private String overview;

    private String original_language;

    public Movie () {

    }
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", movie_id=" + movie_id +
                ", vote_average=" + vote_average +
                ", movie_overview='" + overview + '\'' +
                ", original_language='" + original_language + '\'' +
                '}';
    }
}
