package com.travelwithme.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.button.MaterialButton;
import com.travelwithme.Database.DatabaseHelper;
import com.travelwithme.R;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton btnLogin;
    private AppCompatTextView tvNewAccount;
    private AppCompatEditText etUsername, etPassword;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        initViews();
        initObjects();
        initListeners();
    }

    private void initViews() {
        btnLogin = findViewById(R.id.btnLogin);
        tvNewAccount = findViewById(R.id.tvNewAccount);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(this);
    }

    private void initListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields should not be empty", Toast.LENGTH_SHORT).show();
                } else {
                    validateUser();
                }
            }
        });
        tvNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void validateUser() {
        String validateUser = databaseHelper.validateUser(
                etUsername.getText().toString(),
                etPassword.getText().toString()
        );
        if (validateUser != null) {
            saveUserIdInCache(validateUser);
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserIdInCache(String userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("EASY-TRAVEL", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("TOKEN", userId);
        myEdit.apply();
    }
}