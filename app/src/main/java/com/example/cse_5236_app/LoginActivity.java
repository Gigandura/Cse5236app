package com.example.cse_5236_app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cse_5236_app.ui.Login.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("LoginActivity", "LoginActivity OnCreate");
        setContentView(R.layout.activity_login);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_login);
        if (fragment == null) {
            fragment = new LoginFragment();
            fm.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_login, fragment)
                    .commit();
        }



    }

}
