package com.example.demosyno.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.demosyno.model.Country
import com.example.demosyno.model.CountryItem

@Dao
interface RoomDao {
    @Query("SELECT * FROM countries")
    fun getAllRecords(): LiveData<MutableList<CountryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(countryItem: CountryItem)

    @Query("DELETE FROM countries")
    fun deleteAllRecords()
}