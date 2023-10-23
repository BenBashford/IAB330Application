package com.example.navigationprototype.Utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.room.Room;

import com.example.navigationprototype.DB.AppDatabase;
public class MyApp extends Application{
    private static AppDatabase appDatabase;
    private static String DATABASE_NAME = "EmergenciesDB";

    public static final String PREF_NAME = "MyAppPrefs";
    private static SharedPreferences preferences;
    public static boolean isNight = false;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Test", "Test");
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME).build();
        preferences = getSharedPreferences(PREF_NAME,
                MODE_PRIVATE);
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
    public static SharedPreferences getPreferences() {
        return preferences;
    }
}
