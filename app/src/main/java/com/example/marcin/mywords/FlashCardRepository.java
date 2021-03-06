package com.example.marcin.mywords;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.os.AsyncTask;

import java.util.List;
// " Best practice "
public class FlashCardRepository {

    private FlashCardDao flashCardDao;
    private LiveData<List<FlashCard>> allFlashCards;
    private  List<FlashCard> ViewFlashCardList;
    private  List<FlashCard> findViewFlashCardList;


    public FlashCardRepository(Application application){
        AppDatabase appDb;
        appDb = AppDatabase.getDatabase(application);
        flashCardDao = appDb.flashCardDao();
        allFlashCards=flashCardDao.getAllFlashCards();
        ViewFlashCardList=flashCardDao.getAll();
    }
        //room zalacza wszystkie queries na osobnych threadach
    //Obserwowana livedata powiadomi obserwatora przy zmianie
    LiveData<List<FlashCard>> getAllFlashCards() {
        return allFlashCards;
    }

    List<FlashCard> getAll(){return ViewFlashCardList;
    }


    List<FlashCard> findFlashCard(String definition){
        findViewFlashCardList=flashCardDao.findFlashCard(definition);
        return findViewFlashCardList;
    }



    public void insertFlashCard(FlashCard newflashcard) {
        new InsertAsyncTask(flashCardDao).execute(newflashcard);

    }
    public void deleteAllFlashCards(){
        new DeleteAllAsyncTask(flashCardDao).execute();
    }

    public void deleteFlashCard(FlashCard newflashcard) {
        DeleteAsyncTask task = new DeleteAsyncTask(flashCardDao);
        task.execute(newflashcard);
    }


//    -------------------------------INSERT TASK-----------------------------------
    private static class InsertAsyncTask extends AsyncTask<FlashCard, Void, Void> {

        private FlashCardDao asyncTaskDao;

        InsertAsyncTask(FlashCardDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FlashCard... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
    //    -------------------------------DELETE TASK-----------------------------------

    private static class DeleteAsyncTask extends AsyncTask<FlashCard, Void, Void> {

        private FlashCardDao asyncTaskDao;

        DeleteAsyncTask(FlashCardDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FlashCard... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }
    //    -------------------------------DELETE ALL TASK-----------------------------------

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private FlashCardDao asyncTaskDao;

        DeleteAllAsyncTask(FlashCardDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            asyncTaskDao.deleteAllFlashCards();
            return null;
        }
    }

}
