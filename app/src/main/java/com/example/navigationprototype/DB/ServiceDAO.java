package com.example.navigationprototype.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ServiceDAO {
        @Insert
        void Insert(Service service);

        @Update
        void Update(Service service);

        @Delete
        void Delete(Service service);

        @Query("SELECT * FROM Services")
        LiveData<List<Service>> getAllservices();

        @Query("Select * FROM Services WHERE catagory = :catagory")
        LiveData<List<Service>> getCurrentServices(int catagory);

}
