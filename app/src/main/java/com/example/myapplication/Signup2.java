package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Calendar;

public class Signup2 extends AppCompatActivity implements View.OnClickListener{
    private Button signupBtn, cancelBtn;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private DatePicker datePicker;
    private String fullName, email, username, password,degree, gender, dateOfBirth;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        //set up button
        signupBtn = (Button) findViewById(R.id.saveBtn);
        cancelBtn = (Button) findViewById(R.id.cancelButton);
        signupBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        //get data from previous activity
        collectData();

        mAuth = FirebaseAuth.getInstance();



        //set up ratio group 
//        maleBtn = (RadioButton) findViewById(R.id.radioButton3);
//        femaleBtn = (RadioButton) findViewById(R.id.radioButton2);
//        otherBtn = (RadioButton) findViewById(R.id.radioButton1);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //initialize
        radioGroup.clearCheck();
        //add check which button is selected 



        //set up date picker
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.setSpinnersShown(true);

        gender = "unselected";
    }

    private void collectData() {
        Intent intent = getIntent();
        fullName = intent.getStringExtra("fullName");
        email = intent.getStringExtra("email");
        username = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");
        degree = intent.getStringExtra("degree");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveBtn:
                userSignup();
                break;
            case R.id.cancelBtn:
                startActivity(new Intent(Signup2.this, LoginActivity.class));
        }

    }

    private void userSignup() {
        //get date
        int date = datePicker.getDayOfMonth();
        //get month
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        dateOfBirth = date +"/" + month + "/" + year;

        gender = radioButton.getText().toString();
        if (gender.equals("unselected")) {
            radioGroup.requestFocus();
            Toast.makeText(Signup2.this, "Please select gender", Toast.LENGTH_LONG).show();
            return;
        }
        if (dateOfBirth.isEmpty()) {
            datePicker.requestFocus();
            Toast.makeText(Signup2.this, "Please select date of birth", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(email,password,fullName,username,
                                    gender,dateOfBirth,degree);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                //register successfully
                                                Toast.makeText(Signup2.this, "User has been registered successfully!",Toast.LENGTH_LONG).show();

                                            } else {
                                                //there is some error.
                                                Toast.makeText(Signup2.this, "Failed to sign in! Try again!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                            //new user will direct to assignment activity
                            startActivity(new Intent(Signup2.this, AssignmentActivity.class));
                        } else {
                            Toast.makeText(Signup2.this, "Failed to sign in! Try again! email and password", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}