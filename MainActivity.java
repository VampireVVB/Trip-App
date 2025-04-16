package com.example.trip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button loginBtn, signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);

        loginBtn.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        signupBtn.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }
}
