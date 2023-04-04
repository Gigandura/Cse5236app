package com.example.cse_5236_app.ui.Login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cse_5236_app.model.User;
import com.example.cse_5236_app.ui.MainActivity;
import com.example.cse_5236_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;

public class LoginFragment extends DialogFragment implements View.OnClickListener {

    public LoginFragment(){
        super(R.layout.fragment_login);
    }

    protected EditText username;
    protected EditText password;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Log.v("LoginFragment", "LoginFragment OnCreateView");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        username = v.findViewById(R.id.username_text);
        password = v.findViewById(R.id.password_text);

        Button login = (Button) v.findViewById(R.id.login_button);
        Button newuserb = (Button) v.findViewById(R.id.new_user_button);

        login.setOnClickListener(this);
        newuserb.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {
            String userId = username.getText().toString();
            String usernameText = username.getText().toString();
            String passwordText = password.getText().toString();
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            mDatabase.child("users").child(userId).get().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    try {
                        Log.v("LoginFragment", usernameText);
                        User testing = task.getResult().getValue(User.class);
                        Log.v("LoginFragment", testing.getPassword() + " " + usernameText);
                        if (testing.getUsername().equals(usernameText) && testing.getPassword().equals(passwordText)) {
                            // Correct username and password path
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(getString(R.string.saved_username_key), usernameText);
                            editor.apply();
                            Log.v("Login Fragment", "Written to shared storage");
                            startActivity(intent);
                        } else {
                            // Wrong username or password path
                            DialogFragment newFragment = new LoginFragment();
                            newFragment.show(getActivity().getSupportFragmentManager(), "Login Fragment");
                        }
                    } catch (Exception e) {
                        Log.e("Login Fragment" , e.toString());
                    }

                }
            });

        } else if (v.getId() == R.id.new_user_button) {
            writeNewUser(username.getText().toString(), username.getText().toString()
                    , password.getText().toString());
        } else {
            Log.e("Login Fragment", "Bad button input");
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v("Login Fragment", "On Destroy View Method");
    }

    public void writeNewUser(String userId, String name, String password) {
        User user = new User(name, password);

        mDatabase.child("users").child(userId).setValue(user);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_login_fail)
                .setPositiveButton(R.string.dialog_ok_button, (dialog, id) -> {
                    //close the window
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
