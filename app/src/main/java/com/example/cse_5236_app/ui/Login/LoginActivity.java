package com.example.cse_5236_app.ui.Login;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cse_5236_app.R;
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

    @Override
    public void onStart() {
        super.onStart();
        Log.v("LoginActivity", "On Start Method");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("LoginActivity", "On Resume Method");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.v("LoginActivity", "On Pause Method");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.v("LoginActivity", "On Stop Method");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("LoginActivity", "On Destroy Method");
    }
    @Override
    public void onRestart() {
        super.onRestart();
        Log.v("LoginActivity", "On Restart Method");
    }
}
