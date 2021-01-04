package com.ashn.firebaselogin_registrationemail_password;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ashn.firebaselogin_registrationemail_password.databinding.ActivityRegistrationBinding;

public class Registration extends AppCompatActivity {

    ActivityRegistrationBinding registrationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
}