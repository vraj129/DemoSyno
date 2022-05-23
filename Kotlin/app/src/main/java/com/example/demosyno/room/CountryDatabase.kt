package com.example.demosyno.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.demosyno.model.CountryItem
import com.example.demosyno.model.TypeConverterCountryInfo

@Database(entities = [CountryItem :: class], version = 2, exportSchema = false)
@TypeConverters(TypeConverterCountryInfo::class)
abstract class CountryDatabase :RoomDatabase() {

    abstract fun getRoomDao():RoomDao
    companion object{
        private var DB_INSTANCE:CountryDatabase? = null

        fun getDatabase(context: Context):CountryDatabase{
            if(DB_INSTANCE == null){
                DB_INSTANCE = Room.databaseBuilder(
                    context,
                    CountryDatabase::class.java,
                    "countriesDB"
                ).allowMainThreadQueries().build()
            }
            return DB_INSTANCE!!
        }
    }

}