package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    private TextView fullNameTV, emailTV, degreeTV, genderTV, dobTV;
    private TextInputEditText fullNameEditText, emailEditText, degreeEditText;
    private Button updateBtn;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userId = user.getUid();

        //display
        fullNameTV = (TextView) findViewById(R.id.user_name);
        emailTV = (TextView) findViewById(R.id.user_email);
        degreeTV = (TextView) findViewById(R.id.user_degree);
        genderTV = (TextView) findViewById(R.id.user_gender);
        dobTV = (TextView) findViewById(R.id.user_birth);

        //Change
        fullNameEditText = (TextInputEditText) findViewById(R.id.fullNameChange);
//        emailEditText = (TextInputEditText) findViewById(R.id.emailChange);
        degreeEditText = (TextInputEditText) findViewById(R.id.degreeChange);

        //btn
        updateBtn = (Button) findViewById(R.id.updateButton);
        updateBtn.setOnClickListener(this);


        //obtain current user's details
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String fullName = userProfile.getFullName();
                    String email = userProfile.getEmail();
                    String degree = userProfile.getDegree();
                    String gender = userProfile.getGender();
                    String dob = userProfile.getDateOfBirth();

                    fullNameTV.setText(fullName);
                    emailTV.setText(email);
                    degreeTV.setText(degree);
                    genderTV.setText(gender);
                    dobTV.setText(dob);
                    fullNameEditText.setText(fullName);
                    emailEditText.setText(email);
                    degreeEditText.setText(degree);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something Wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        //update the profile detail

    }
}