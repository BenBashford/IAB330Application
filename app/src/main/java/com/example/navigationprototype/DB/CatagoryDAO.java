package com.example.navigationprototype.DB;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.navigationprototype.DB.Catagory;

import java.util.List;

@Dao
public interface CatagoryDAO {
    @Insert
    void Insert(Catagory catagory);

    @Update
    void Update(Catagory catagory);

    @Delete
    void Delete(Catagory catagory);

    @Query("SELECT * FROM catagories")
    LiveData<List<Catagory>> getAllcatagories();

}
