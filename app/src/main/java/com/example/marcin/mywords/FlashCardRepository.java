package com.example.marcin.mywords;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

public class FlashCardRepository {

    private MutableLiveData<List<FlashCard>> searchResults =
            new MutableLiveData<>();



    private FlashCardDao flashCardDao;
    private LiveData<List<FlashCard>> allFlashCards;


    public FlashCardRepository(Application application){
        AppDatabase appDb;
        appDb = AppDatabase.getDatabase(application);
        flashCardDao = appDb.flashCardDao();
        allFlashCards=flashCardDao.getAllFlashCards();
    }
        //room zalacza wszystkie queries na osobnych threadach
    //Obserwowana livedata powiadomi obserwatora przy zmianie
    LiveData<List<FlashCard>> getAllFlashCards() {
        return allFlashCards;
    }
    private void asyncFinished(List<FlashCard> results){
        searchResults.setValue(results);
    }
    public void insertFlashCard(FlashCard newflashcard) {
        InsertAsyncTask task = new InsertAsyncTask(flashCardDao);
        task.execute(newflashcard);
    }

    /*public void deleteFlashCard(FlashCard newflashcard) {
        DeleteAsyncTask task = new DeleteAsyncTask(flashCardDao);
        task.execute(newflashcard);
    }*/

 /*   public void findFlashCard(FlashCard newflashcard) {
        QueryAsyncTask task = new QueryAsyncTask(flashCardDao);
        task.delegate = this;
        task.execute(newflashcard);
    }
*/







//Na ten moment niepotrzebne
    /*private static class QueryAsyncTask extends
            AsyncTask<String,Void,List<FlashCard>>{
        private FlashCardDao asyncTaskDao;
        private FlashCardRepository delegate =null;

        QueryAsyncTask(FlashCardDao dao){
            asyncTaskDao=dao;
        }
        @Override
        protected List<FlashCard> doInBackground(final String... params) {
            return asyncTaskDao.findFlashCard(params[0]);
        }

        @Override
        protected void onPostExecute(List<FlashCard> result) {
            delegate.asyncFinished(result);
        }
    }*/

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
}
