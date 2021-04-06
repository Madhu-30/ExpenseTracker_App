package com.example.myexpenditure;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MainData.class}, version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static com.example.myexpenditure.RoomDB database;
    private static String DATABASE_NAME ="database";

    public synchronized static com.example.myexpenditure.RoomDB getInstance(Context context){

        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                    com.example.myexpenditure.RoomDB.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }

    public abstract MainDao mainDao();

}
