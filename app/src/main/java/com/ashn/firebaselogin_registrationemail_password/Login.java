package com.ashn.firebaselogin_registrationemail_password;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ashn.firebaselogin_registrationemail_password.databinding.ActivityLoginBinding;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    ActivityLoginBinding loginBinding;

    private TextInputEditText email, password;
    private TextView loginBtn, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = loginBinding.getRoot();
        setContentView(view);

        String Email = loginBinding.email.getText().toString().trim();
        if (Email.isEmpty()){
            email.setError("Missing Email");
            return;
        }else {
            email.setError(null);
        }

        String Password = loginBinding.password.getText().toString().trim();
        if(Password.isEmpty()){
            password.setError("Missing Password");
        }else {
            password.setError(null);
        }

    }
}