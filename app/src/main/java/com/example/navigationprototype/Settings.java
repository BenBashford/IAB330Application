package com.example.navigationprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.navigationprototype.Utils.MyApp;

public class Settings extends AppCompatActivity {
    private ImageView homeButton;
    private ImageView criticalButton;
    private ImageView nearbyButton;
    private ImageView settingsButton;
    private Switch switchTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setViewIds();

        homeButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Landing.class);
            startActivity(intent);
        });
        criticalButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    CriticalServices.class);
            startActivity(intent);
        });
        nearbyButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    NearYou.class);
            startActivity(intent);
        });
        settingsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Settings.class);
            startActivity(intent);
        });

        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == MyApp.isNight)
                    return;
                MyApp.isNight = isChecked;
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        switchTheme.setChecked(MyApp.isNight);

        Spinner spinnerLanguage = findViewById(R.id.spinner_language);
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG222", String.valueOf(parent.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setViewIds() {
        homeButton = findViewById(R.id.home);
        criticalButton= findViewById(R.id.critical);
        nearbyButton = findViewById(R.id.nearby);
        settingsButton = findViewById(R.id.settings);
        switchTheme = findViewById(R.id.switch1);
    }
}