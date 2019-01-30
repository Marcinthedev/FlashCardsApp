package com.example.marcin.mywords;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
//Stworzenie bazy danych w Room
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

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // wpisywanie wstepnych danych
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
            flashCardnew.setDefinitionDb("a small domesticated carnivorous mammal with soft fur, " +
                    "a short snout, and retractable claws. It is widely kept as a pet or for catching mice," +
                    " and many breeds have been developed.");
            flashCardDao.insert(flashCardnew);
            flashCardnew = new FlashCard();
            flashCardnew.setWordDb("dog");
            flashCardnew.setDefinitionDb("a domesticated carnivorous mammal that typically has a long snout," +
                    " an acute sense of smell, non-retractable claws, and a barking, howling, or whining voice.");
            flashCardDao.insert(flashCardnew);

            return null;
        }
    }
}