package com.example.navigationprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.navigationprototype.DB.Catagory;
import com.example.navigationprototype.DB.CatagoryDAO;
import com.example.navigationprototype.DB.Service;
import com.example.navigationprototype.DB.ServiceDAO;
import com.example.navigationprototype.Utils.MyApp;

import java.util.ArrayList;
import java.util.List;

public class Services extends AppCompatActivity {


    private ImageView homeButton;
    private ImageView criticalButton;
    private ImageView nearbyButton;
    private ImageView settingsButton;

    private RecyclerView recyclerView;

    ArrayList<Service> services;

    ServicesAdapter Sadapter;


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

        services=new ArrayList<>();
        Intent intent = getIntent();
        int catagory = intent.getExtras().getInt("id");
        GetDbContent(catagory);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Sadapter = new ServicesAdapter(services);
        recyclerView.setAdapter(Sadapter);

        Sadapter.setOnItemClickListener(position -> {
            int serviceid = services.get(position).getId();
            String testoutput = String.valueOf(serviceid);

            Toast.makeText(Services.this, testoutput, Toast.LENGTH_SHORT).show();
            Intent toInformation = new Intent( Services.this, Information.class);
            toInformation.putExtra("id",serviceid);
            startActivity(toInformation);
        });

    }

    private void GetDbContent(int catagory) {
        ServiceDAO serviceDao = MyApp.getAppDatabase().serviceDao();

        //AsyncTask.execute(() -> {
        //    serviceDao.Insert(new Service("test1","Lorem Ipsum", 1, 1, 1, 1, "w"));
        //});

        LiveData<List<Service>> ServicesLiveData = serviceDao.getCurrentServices(catagory);
        ServicesLiveData.observe(this, CList -> {
            // Handle the list of vehicles here
            services.clear();
            services.addAll(CList);
            Sadapter.notifyDataSetChanged();
        });
    }

    private void setViewIds() {
        homeButton = findViewById(R.id.home);
        criticalButton= findViewById(R.id.critical);
        nearbyButton = findViewById(R.id.nearby);
        settingsButton = findViewById(R.id.settings);
    }
}