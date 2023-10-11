package com.example.navigationprototype.DB;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Catagory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CatagoryDAO catagoryDao();
}
