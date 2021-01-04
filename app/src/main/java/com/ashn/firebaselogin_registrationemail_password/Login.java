package com.ashn.firebaselogin_registrationemail_password;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ashn.firebaselogin_registrationemail_password.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    ActivityLoginBinding loginBinding;
    private FirebaseAuth mAuth;

    private String lEmail=null,lPassword=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = loginBinding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        loginBinding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSignup = new Intent(Login.this, Registration.class);
                startActivity(iSignup);
                finish();
            }
        });

        loginBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin(lEmail, lPassword);
            }
        });

    }

    private void userLogin(String lEmail, String lPassword) {

        lEmail = loginBinding.email.getText().toString().trim();
        if (lEmail.isEmpty()){
            loginBinding.email.setError("Missing Email");
            return;
        }else {
            loginBinding.email.setError(null);
        }

        lPassword = loginBinding.password.getText().toString().trim();
        if(lPassword.isEmpty()){
            loginBinding.password.setError("Missing Password");
        }else {
            loginBinding.password.setError(null);
        }

        mAuth.signInWithEmailAndPassword(lEmail, lPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d("Success", "signInWithEmail:success");
                            Toast.makeText(Login.this, "Logged In",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w("Failure", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if (user!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),Login.class));
    }


}