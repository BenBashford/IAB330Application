package com.example.navigationprototype;

import android.os.AsyncTask;

import com.example.navigationprototype.DB.Catagory;
import com.example.navigationprototype.DB.CatagoryDAO;
import com.example.navigationprototype.DB.Service;
import com.example.navigationprototype.DB.ServiceDAO;

public class DataPopulation {
    private final CatagoryDAO categoryDAO;
    private final ServiceDAO serviceDAO;

    public DataPopulation(CatagoryDAO categoryDAO, ServiceDAO serviceDAO) {
        this.categoryDAO = categoryDAO;
        this.serviceDAO = serviceDAO;
    }

    public void clear(){
        AsyncTask.execute(() -> {
        categoryDAO.deleteAllCategories();
        categoryDAO.clearPrimaryKeyIndex();
        serviceDAO.deleteAllServices();
        });
    }
    public void populateData() {
        // Insert instances into the databases
        AsyncTask.execute(() -> {
        categoryDAO.Insert(new Catagory("Critical Emergency"));
        categoryDAO.Insert(new Catagory("Non-Critical Emergency"));
        categoryDAO.Insert(new Catagory("Report a Crime In Progress"));
        categoryDAO.Insert(new Catagory("Input Specific Emergency"));

        serviceDAO.Insert(new Service("Police Station", "Brisbane City Police Station", 1, -27.472861860765686, 153.02628136458495, 123, "16 Mary St, Brisbane City QLD 4000"));
        serviceDAO.Insert(new Service("Fire Station", "Roma Street Fire and Rescue Station", 1, -27.46551257100025, 153.01529174783272, 456, "279 Roma St, Brisbane City QLD 4000"));
        serviceDAO.Insert(new Service("Hospital", "Olive Sacred Heart Hospital", 1, -27.465564269714765, 153.02305829313966, 456, "232 Wickham Terrace, Brisbane City QLD 4000bris"));

        serviceDAO.Insert(new Service("Storm Support", "Storm Support Services - Hail Damage Specialists", 2, -27.466823876280834, 153.02752500368214, 456, "Brisbane Club Tower, 406/241 Adelaide St, Brisbane City QLD 4000"));
        serviceDAO.Insert(new Service("House Call Doctor", "House Call Doctor - After Hours Home Doctors Sunshine Coast", 2, -27.462093400571575, 153.02668352250976, 456, "196 Wharf St, Spring Hill QLD 4000"));
        serviceDAO.Insert(new Service("Veterinary", "Central New Farm Veterinary Surgery", 2, -27.46487242307142, 153.04374525180714, 456, "764 Brunswick St, New Farm QLD 4005"));
//        // Add more instances as needed
        });
    }
}