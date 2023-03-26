package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Signup extends AppCompatActivity implements View.OnClickListener{
    private Button nextButton, cancelButton;
    private TextInputEditText fullNameTextView, emailTextView, userTextView, passwordTextView, degreeTextView;
    private DatabaseReference databaseReference;
    private String result = "test";
    private List<User> allUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //set up button
        nextButton = (Button) findViewById(R.id.nextBtn);
        cancelButton = (Button) findViewById(R.id.cancelBtn);
        nextButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        //set up text view
        fullNameTextView = (TextInputEditText) findViewById(R.id.fullName);
        emailTextView = (TextInputEditText) findViewById(R.id.email);
        userTextView = (TextInputEditText) findViewById(R.id.username);
        passwordTextView = (TextInputEditText) findViewById(R.id.password);
        degreeTextView = (TextInputEditText) findViewById(R.id.degree);

        //database
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        allUser = new ArrayList<>();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:
                transfer();
                break;
            case R.id.cancelBtn:
                startActivity(new Intent(Signup.this, LoginActivity.class));
        }

    }

    private void transfer() {
        //collect data
        String fullName = fullNameTextView.getText().toString();
        String email = emailTextView.getText().toString();
        String username = userTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String degree = degreeTextView.getText().toString();

        if (fullName.isEmpty()) {
            fullNameTextView.setError("Full name is required!");
            fullNameTextView.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailTextView.setError("Email is required!");
            emailTextView.requestFocus();
            return;
        }

        if (email.length() != 23) {
            emailTextView.setError("Enter correct email address.");
            emailTextView.requestFocus();
            return;
        }
//        if(emailIsExist(email)) {
//            emailTextView.setError("Change a new email");
//            emailTextView.requestFocus();
//            return;
//        }

        if (username.isEmpty()) {
            userTextView.setError("Username is required!");
            userTextView.requestFocus();
            return;
        }
        //make sure the username is unique check all username
//        if(userNameIsExist(username)) {
//            userTextView.setError("Change a new username");
//            userTextView.requestFocus();
//            return;
//        }

        if (password.isEmpty()) {
            passwordTextView.setError("Password is required!");
            passwordTextView.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passwordTextView.setError("Min password length should be 6 characters!");
            passwordTextView.requestFocus();
            return;
        }

        //transfer data
        Intent intent = new Intent(Signup.this, Signup2.class);
        intent.putExtra("fullName", fullName);
        intent.putExtra("email", email);
        intent.putExtra("userName", username);
        intent.putExtra("password", password);
        intent.putExtra("degree", degree);
        startActivity(intent);
    }

    private boolean emailIsExist(String emailTest) {
        allUser = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()) {
                    User user = data.getValue(User.class);
                    allUser.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        for (int i = 0; i < allUser.size(); i++) {
            if (emailTest.equals(allUser.get(i).getEmail())) {
                return true;
            }
        }
        return false;
    }

    private boolean userNameIsExist(String test) {

        allUser = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds: snapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    allUser.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        for (int i = 0; i < allUser.size(); i++) {
            if (test.equals(allUser.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }


}