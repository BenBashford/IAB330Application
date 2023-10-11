package com.example.navigationprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class Categories extends AppCompatActivity {

    private ImageView homeButton;
    private ImageView criticalButton;
    private ImageView nearbyButton;
    private ImageView settingsButton;

    private Button servicesButton1;

    private Button servicesButton2;

    private Button servicesButton3;

    private Button servicesButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
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
        servicesButton1.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Services.class);
            startActivity(intent);
        });
        servicesButton2.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Services.class);
            startActivity(intent);
        });servicesButton3.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Services.class);
            startActivity(intent);
        });
        servicesButton4.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Services.class);
            startActivity(intent);
        });


    }

    private void setViewIds() {
        homeButton = findViewById(R.id.home);
        criticalButton= findViewById(R.id.critical);
        nearbyButton = findViewById(R.id.nearby);
        settingsButton = findViewById(R.id.settings);
        servicesButton1= findViewById(R.id.button1);
        servicesButton2= findViewById(R.id.button2);
        servicesButton3= findViewById(R.id.button3);
        servicesButton4= findViewById(R.id.button4);


    }
}