package com.example.marcin.mywords;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {FlashCard.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //uzywanie metod z dao
    public abstract FlashCardDao flashCardDao();
//singleton
    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (AppDatabase.class){
                if (INSTANCE==null){
                    INSTANCE=Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,"app_database").build();
                }
            }
        }
        return INSTANCE;
    }
    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    //private static override RoomDatabase.Callback()#onCreate
}