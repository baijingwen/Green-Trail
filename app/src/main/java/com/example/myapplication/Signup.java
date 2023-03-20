package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    private Button nextButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //next to signup2
        //login to main
        Button nextButton = findViewById(R.id.nextBtn);
        Button loginButton = findViewById(R.id.loginBtn);
        nextButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBtn:
                startActivity(new Intent(Signup.this, LoginActivity.class));
                break;
            case R.id.nextBtn:

                startActivity(new Intent(Signup.this, Signup2.class));
                break;
        }
    }

    private void transfer() {

    }
}

