package com.example.trip;


import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText emailInput, passwordInput;
    Button loginBtn;
    DBHelper db;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);
        session = new SessionManager(this);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String pass = passwordInput.getText().toString();

            if (db.loginUser(email, pass)) {
                session.saveLogin(email);
                startActivity(new Intent(this, WelcomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
