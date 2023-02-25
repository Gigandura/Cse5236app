package com.example.cse_5236_app.ui.Login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.cse_5236_app.R;

public class LoginFragment extends Fragment {

    public LoginFragment(){
        super(R.layout.fragment_login);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Log.v("LoginFragment", "LoginFragment OnCreateView");

        Button login = (Button) v.findViewById(R.id.login_button);
        Button cancel = (Button) v.findViewById(R.id.cancel_button);
        Button newuser = (Button) v.findViewById(R.id.new_user_button);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Check username and password then
                //Go to main activity something
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v("Login Fragment", "On Destroy View Method");
    }
}
