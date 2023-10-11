package com.example.navigationprototype;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.navigationprototype.DB.Catagory;
import com.example.navigationprototype.DB.CatagoryDAO;
import com.example.navigationprototype.Utils.MyApp;

import java.util.ArrayList;
import java.util.List;

public class Categories extends AppCompatActivity {

    private ImageView homeButton;
    private ImageView criticalButton;
    private ImageView nearbyButton;
    private ImageView settingsButton;
    private RecyclerView recyclerView;
    private CatagoriesAdapter Cadapter;

    ArrayList<Catagory> catagories;

    @SuppressLint("MissingInflatedId")
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

        catagories=new ArrayList<>();
        GetDbContent();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cadapter = new CatagoriesAdapter(catagories);
        recyclerView.setAdapter(Cadapter);

        Cadapter.setOnItemClickListener(position -> {
            int catagoryid = catagories.get(position).getId();
            String testoutput = String.valueOf(catagoryid);
            Toast.makeText(Categories.this, testoutput, Toast.LENGTH_SHORT).show();
            Intent toServices = new Intent( Categories.this, Services.class);
            toServices.putExtra("id",catagoryid);
            startActivity(toServices);
        });


    }
    private void GetDbContent() {
        CatagoryDAO catagoryDao = MyApp.getAppDatabase().catagoryDao();
        LiveData<List<Catagory>> CatagoriesLiveData = catagoryDao.getAllcatagories();
        CatagoriesLiveData.observe(this, CList -> {
            // Handle the list of vehicles here
            catagories.clear();
            catagories.addAll(CList);
            Cadapter.notifyDataSetChanged();
        });
    }

    private void setViewIds() {
        homeButton = findViewById(R.id.home);
        criticalButton= findViewById(R.id.critical);
        nearbyButton = findViewById(R.id.nearby);
        settingsButton = findViewById(R.id.settings);


    }
}