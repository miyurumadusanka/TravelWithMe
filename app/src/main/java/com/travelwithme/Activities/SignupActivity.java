package com.travelwithme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.button.MaterialButton;
import com.travelwithme.Database.DatabaseHelper;
import com.travelwithme.R;

import java.util.UUID;

public class SignupActivity extends AppCompatActivity {

    private MaterialButton btnSignUp;
    private AppCompatTextView tvLogin;
    private AppCompatEditText etEmail,etUsername, etPassword;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        initViews();
        initObjects();
        initListeners();
    }

    private void initViews() {
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(this);
    }

    private void initListeners() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().isEmpty() || etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Fields should not be empty", Toast.LENGTH_SHORT).show();
                } else {
                    insertUser();
                }
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void insertUser() {
        databaseHelper.insertUser(
                UUID.randomUUID().toString(),
                etEmail.getText().toString(),
                etUsername.getText().toString(),
                etPassword.getText().toString()
        );
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}