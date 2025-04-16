package com.example.trip;


import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Context context;

    public SessionManager(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("userSession", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void saveLogin(String email) {
        editor.putString("email", email);
        editor.apply();
    }

    public String getEmail() {
        return sp.getString("email", null);
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }

    public void saveSpinnerPosition(int pos) {
        editor.putInt("spinner_pos", pos);
        editor.apply();
    }

    public int getSpinnerPosition() {
        return sp.getInt("spinner_pos", 0);
    }
}
