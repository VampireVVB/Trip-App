package com.example.trip;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText email, password;
    Button signup;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBHelper(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signupBtn);

        signup.setOnClickListener(v -> {
            if (db.registerUser(email.getText().toString(), password.getText().toString())) {
                Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Email already used", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
