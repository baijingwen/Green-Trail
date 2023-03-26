package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText editTextEmail, editTextPassword;
    private Button login, signUp;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //click to login based on entered information
        login = (Button) findViewById(R.id.loginToAppButton);
        signUp = (Button) findViewById(R.id.signupToAppButton);
        login.setOnClickListener(this);
        signUp.setOnClickListener(this);

        editTextEmail = (TextInputEditText) findViewById(R.id.textEditUserName);
        editTextPassword = (TextInputEditText) findViewById(R.id.textEditPassword);
        mAuth = FirebaseAuth.getInstance();
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loginToAppButton:
                    userLogin();
                    startActivity(new Intent(LoginActivity.this, AssignmentActivity.class));
                    break;
                case R.id.signupToAppButton:
                    startActivity(new Intent(LoginActivity.this, Signup.class));
            }
        }
        private void userLogin() {
            //collect user's information
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            //check whether user enter all data
            if (email.isEmpty()) {
                editTextEmail.setError("Email is required!");
                editTextEmail.requestFocus();
                return;
            }
            if(password.isEmpty()) {
                editTextPassword.setError("Password is required!");
                editTextPassword.requestFocus();
                return;
            }
            if (password.length() < 6) {
                editTextPassword.setError("Min password length is 6 characters!");
                editTextPassword.requestFocus();
                return;
            }

            //check whether the user is already in system and the data entered is correct or not.
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(LoginActivity.this,AssignmentActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Failed to login",Toast.LENGTH_LONG).show();
                    }
                }
            });
    }
}