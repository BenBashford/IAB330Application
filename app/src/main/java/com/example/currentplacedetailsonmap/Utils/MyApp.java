package com.example.currentplacedetailsonmap.Utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.room.Room;

import com.example.currentplacedetailsonmap.DB.AppDatabase;

import org.xutils.BuildConfig;
import org.xutils.x;

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
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
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
