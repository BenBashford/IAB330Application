package com.example.navigationprototype;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class CriticalServices extends AppCompatActivity {


    private ImageView homeButton;
    private ImageView criticalButton;
    private ImageView nearbyButton;
    private ImageView settingsButton;
    private Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critical_services);
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
        mapButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    MapToHereActivity.class);
            startActivity(intent);
        });
    }

    private void setViewIds() {
        homeButton = findViewById(R.id.home);
        criticalButton= findViewById(R.id.critical);
        nearbyButton = findViewById(R.id.nearby);
        settingsButton = findViewById(R.id.settings);
        mapButton = findViewById(R.id.mapButton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE,ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION}, 1000);
        }
    }
}