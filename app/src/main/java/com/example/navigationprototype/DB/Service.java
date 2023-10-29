package com.example.navigationprototype.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Services")
public class Service {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int catagory;
    public String description;
    public double latitude;
    public double longditude;
    public int number;
    public String adress;

    public Service (String name, String description, int catagory, double latitude, double longditude, int number, String adress) {
        this.name = name;
        this.description = description;
        this.catagory = catagory;
        this.latitude = latitude;
        this.longditude = longditude;
        this.number = number;
        this.adress= adress;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getCatagory() {
        return catagory;
    }
    public void setCatagory(int catagory) {
        this.catagory = catagory;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longditude;
    }
    public void setLongitudeint (int longditude) {this.longditude = longditude;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

}
