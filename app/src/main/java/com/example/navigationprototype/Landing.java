package com.example.navigationprototype;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS;
import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.navigationprototype.R;

public class Landing extends AppCompatActivity {


    private ImageView homeButton;
    private ImageView criticalButton1;
    private Button criticalButton2;
    private ImageView nearbyButton1;
    private Button nearbyButton2;
    private ImageView settingsButton;

    private Button categoriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        setViewIds();

        homeButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Landing.class);
            startActivity(intent);
            finish();
        });
        criticalButton1.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    CriticalServices.class);
            startActivity(intent);
            finish();
        });
        criticalButton2.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    CriticalServices.class);
            startActivity(intent);
        });
        nearbyButton1.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    NearYou.class);
            startActivity(intent);
            finish();
        });
        nearbyButton2.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    NearYou.class);
            startActivity(intent);
        });
        settingsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Settings.class);
            startActivity(intent);
            finish();
        });
        categoriesButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Categories.class);
            startActivity(intent);
        });

    }

    private void setViewIds() {
        homeButton = findViewById(R.id.home);
        criticalButton1= findViewById(R.id.critical);
        criticalButton2= findViewById(R.id.contactButton);
        nearbyButton1 = findViewById(R.id.nearby);
        nearbyButton2 = findViewById(R.id.servicesButton);
        settingsButton = findViewById(R.id.settings);
        categoriesButton = findViewById(R.id.categoriesButton);
    }
}