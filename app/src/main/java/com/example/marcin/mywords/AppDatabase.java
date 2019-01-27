package com.example.marcin.mywords;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
                            AppDatabase.class,"app_database")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
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
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final FlashCardDao flashCardDao;

        PopulateDbAsync(AppDatabase db) {
            flashCardDao = db.flashCardDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            FlashCard flashCardnew = new FlashCard();
            flashCardnew.setWordDb("cat");
            flashCardnew.setDefinitionDb("bad creature");
            flashCardDao.insert(flashCardnew);
            flashCardnew = new FlashCard();
            flashCardnew.setWordDb("dog");
            flashCardnew.setDefinitionDb("good creature");
            flashCardDao.insert(flashCardnew);

            return null;
        }
    }
}