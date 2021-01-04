package com.ashn.firebaselogin_registrationemail_password;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.ashn.firebaselogin_registrationemail_password.databinding.ActivityMainBinding;
import com.ashn.firebaselogin_registrationemail_password.databinding.ActivityUserDataBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserData extends AppCompatActivity {

    ActivityUserDataBinding userDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userDataBinding = ActivityUserDataBinding.inflate(getLayoutInflater());
        View view = userDataBinding.getRoot();
        setContentView(view);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            userDataBinding.userEmail.setText(email);

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }


    }
}