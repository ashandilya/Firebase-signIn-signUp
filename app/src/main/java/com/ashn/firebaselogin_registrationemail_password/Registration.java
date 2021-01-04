package com.ashn.firebaselogin_registrationemail_password;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ashn.firebaselogin_registrationemail_password.databinding.ActivityLoginBinding;
import com.ashn.firebaselogin_registrationemail_password.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    ActivityRegistrationBinding registrationBinding;
    private FirebaseAuth mAuth;
    private String sEmail=null,sPassword=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registrationBinding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        View view = registrationBinding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        registrationBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSignup = new Intent(Registration.this, Login.class);
                startActivity(iSignup);
                finish();
            }
        });

        registrationBinding.sigunupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser() {

        sEmail = registrationBinding.sEmail.getText().toString().trim();
        /*if (Email.isEmpty()){
            registrationBinding.email.setError("Missing Email");
            return;
        }else {
            registrationBinding.email.setError(null);
        }*/

        sPassword = registrationBinding.sPassword.getText().toString().trim();
        /*if(Password.isEmpty()){
            registrationBinding.password.setError("Missing Password");
        }else {
            registrationBinding.password.setError(null);
        }*/

        mAuth.createUserWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d("Success", "createUserWithEmail:success");
                            Toast.makeText(Registration.this, "Registration Successful.",
                                    Toast.LENGTH_SHORT).show();
                            //FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w("Failure", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
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