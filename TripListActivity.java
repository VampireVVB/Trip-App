package com.example.trip;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class TripListActivity extends AppCompatActivity {
    ListView listView;
    DBHelper db;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        db = new DBHelper(this);
        session = new SessionManager(this);

        listView = findViewById(R.id.tripList);
        ArrayList<String> trips = new ArrayList<>();
        Cursor cursor = db.getTrips(session.getEmail());

        while (cursor.moveToNext()) {
            String dest = cursor.getString(2);
            String date = cursor.getString(3);
            int trav = cursor.getInt(4);
            trips.add(dest + " - " + date + " (" + trav + " travelers)");
        }

        cursor.close();
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, trips));
    }
}
