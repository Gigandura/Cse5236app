package com.example.cse_5236_app.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cse_5236_app.R;
import com.example.cse_5236_app.databinding.FragmentHomeBinding;
import com.example.cse_5236_app.model.User;
import com.example.cse_5236_app.ui.dashboard.MovieRecyclerView;
import com.example.cse_5236_app.ui.dashboard.OnClickMovieListener;
import com.example.cse_5236_app.ui.details.MovieDetailsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment implements OnClickMovieListener {

    private FragmentHomeBinding binding;

    private RecyclerView recyclerView;

    private SharedPreferences sharedPref;
    private MovieRecyclerView adapter;

    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultUsername = "Error";
        String username = sharedPref.getString(getString(R.string.saved_username_key), defaultUsername);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        recyclerView = root.findViewById(R.id.postsRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter = new MovieRecyclerView(this);
        mDatabase.child("users").child(username).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        Log.v("Home Fragment", dataSnapshot.toString());
                        try {
                            user = dataSnapshot.getValue(User.class);
                            adapter.setmMovies(user.getMyList());
                        }
                        catch (Exception e) {
                            Log.e("Notification Fragment", e.toString());
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Notification Fragment", "Picture Database error");
                    }
                });

        recyclerView.setAdapter(adapter);

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMovieClick(int pos) {
        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
        intent.putExtra("movie", adapter.getSelectedMovie(pos));
        try {
            if (getActivity().getIntent().hasExtra("userid")) {
                String userid = getActivity().getIntent().getStringExtra("userid");
                intent.putExtra("userid", userid);

            }
            startActivity(intent);
        }
        catch (Exception e) {
            Log.e("HomeFragment", "Something bad happened");
        }

    }

    @Override
    public void onCategoryClick(String category) {

    }
}