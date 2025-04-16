package com.example.trip;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;


import java.util.Calendar;

public class WelcomeActivity extends AppCompatActivity {
    Spinner destinationSpinner;
    TextView dateText, travelerCount;
    SeekBar travelerSeek;
    Button submitBtn, viewTripsBtn, logoutBtn;

    DBHelper db;
    SessionManager session;

    String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        db = new DBHelper(this);
        session = new SessionManager(this);

        destinationSpinner = findViewById(R.id.spinner);
        dateText = findViewById(R.id.dateText);
        travelerCount = findViewById(R.id.travelerCount);
        travelerSeek = findViewById(R.id.seekBar);
        submitBtn = findViewById(R.id.submitBtn);
        viewTripsBtn = findViewById(R.id.viewTripsBtn);
        logoutBtn = findViewById(R.id.logoutBtn);

        // Restore last spinner choice
        destinationSpinner.setSelection(session.getSpinnerPosition());

        destinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                session.saveSpinnerPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });

        travelerSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                travelerCount.setText("Travelers: " + progress);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        dateText.setOnClickListener(v -> {
            Calendar cal = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, day) -> {
                selectedDate = day + "/" + (month+1) + "/" + year;
                dateText.setText("Date: " + selectedDate);
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
        });

        submitBtn.setOnClickListener(v -> {
            String email = session.getEmail();
            String destination = destinationSpinner.getSelectedItem().toString();
            int travelers = travelerSeek.getProgress();

            if (selectedDate.isEmpty() || travelers == 0) {
                Toast.makeText(this, "Please select date and travelers", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.addTrip(email, destination, selectedDate, travelers)) {
                Toast.makeText(this, "Trip booked!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error saving trip", Toast.LENGTH_SHORT).show();
            }
        });

        viewTripsBtn.setOnClickListener(v -> startActivity(new Intent(this, TripListActivity.class)));

        logoutBtn.setOnClickListener(v -> {
            session.logout();
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        });
    }
}
