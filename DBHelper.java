package com.example.trip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "TravelApp.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE, password TEXT)");
        db.execSQL("CREATE TABLE trips(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, destination TEXT, date TEXT, travelers INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS trips");
        onCreate(db);
    }

    public boolean registerUser(String email, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        long res = db.insert("users", null, values);
        return res != -1;
    }

    public boolean loginUser(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?", new String[]{email, password});
        boolean found = cursor.getCount() > 0;
        cursor.close();
        return found;
    }

    public boolean addTrip(String email, String destination, String date, int travelers) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("destination", destination);
        values.put("date", date);
        values.put("travelers", travelers);
        long res = db.insert("trips", null, values);
        return res != -1;
    }

    public Cursor getTrips(String email) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM trips WHERE email=?", new String[]{email});
    }
}

