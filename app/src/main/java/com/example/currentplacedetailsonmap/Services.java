package com.example.currentplacedetailsonmap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Services extends AppCompatActivity {


    private ImageView homeButton;
    private ImageView criticalButton;
    private ImageView nearbyButton;
    private ImageView settingsButton;

    private Button service1;

    private Button service2;

    private Button service3;

    private Button service4;

    private Button service5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
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
        service1.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Information.class);
            startActivity(intent);
        });
        service2.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Information.class);
            startActivity(intent);
        });
        service3.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Information.class);
            startActivity(intent);
        });service4.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Information.class);
            startActivity(intent);
        });
        service5.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Information.class);
            startActivity(intent);
        });


    }

    private void setViewIds() {
        homeButton = findViewById(R.id.home);
        criticalButton= findViewById(R.id.critical);
        nearbyButton = findViewById(R.id.nearby);
        settingsButton = findViewById(R.id.settings);
        service1 = findViewById(R.id.button1);
        service2 = findViewById(R.id.button2);
        service3 = findViewById(R.id.button3);
        service4 = findViewById(R.id.button4);
        service5 = findViewById(R.id.button5);
    }
}