package com.example.navigationprototype.DB;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Catagories")
public class Catagory {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public Catagory (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}