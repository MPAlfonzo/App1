package com.example.app1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Manejador {
    private static final String SHARED_PREF_NAME = "prodevsblogpref";
    private static final String KEY_USERNAME = "user_name";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_ID = "user_id";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static Manejador mInstance;
    private static Context mCtx;

    private Manejador (Context context) {
        mCtx = context;
    }

    public static synchronized Manejador getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Manejador(context);
        }
        return mInstance;
    }
    public void setUserLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putBoolean(KEY_IS_LOGGED_IN,true);
        editor.apply();
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null)
        );
    }
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}