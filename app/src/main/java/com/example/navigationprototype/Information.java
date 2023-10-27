package com.example.navigationprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigationprototype.DB.Catagory;
import com.example.navigationprototype.DB.CatagoryDAO;
import com.example.navigationprototype.DB.Service;
import com.example.navigationprototype.DB.ServiceDAO;
import com.example.navigationprototype.Utils.MyApp;

import java.util.ArrayList;
import java.util.List;

public class Information extends AppCompatActivity {


    private ImageView homeButton;
    private ImageView criticalButton;
    private ImageView nearbyButton;
    private ImageView settingsButton;

    private TextView infoname;
    private TextView descname;
    private Button online;
    ArrayList<Service> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
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

        Intent intent2 = getIntent();
        int id = intent2.getExtras().getInt("id");
        GetDbContent(1);

    }

    private void setViewIds() {
        homeButton = findViewById(R.id.home);
        criticalButton = findViewById(R.id.critical);
        nearbyButton = findViewById(R.id.nearby);
        settingsButton = findViewById(R.id.settings);


        infoname = findViewById(R.id.service);
        descname = findViewById(R.id.description);
        online = findViewById(R.id.online);
    }

    private void GetDbContent(int id) {
        ServiceDAO serviceDao = MyApp.getAppDatabase().serviceDao();
        services=new ArrayList<>();
        LiveData<List<Service>> ServicesLiveData = serviceDao.getThiseService(id);
        ServicesLiveData.observe(this, CList -> {
            // Handle the list of vehicles here
            services.clear();
            services.addAll(CList);
            updateText();

        });
    }
    private void updateText() {
        Service service = services.get(0);
        infoname.setText(service.getName());
        descname.setText(service.getDescription());
        String url = service.adress;
        online.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });

    }
}