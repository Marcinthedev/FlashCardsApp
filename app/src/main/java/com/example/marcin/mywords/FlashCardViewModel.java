package com.example.marcin.mywords;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

public class FlashCardViewModel extends AndroidViewModel
{
    private FlashCardRepository FCRepository;

    private LiveData<List<FlashCard>> allFlashCards;
    private List<FlashCard> foundFlashCard;

    private MutableLiveData<List<FlashCard>> searchResults;

    public FlashCardViewModel (Application application){
        super(application);
        FCRepository = new FlashCardRepository(application);
        allFlashCards=FCRepository.getAllFlashCards();

    }
//getter
    LiveData<List<FlashCard>> getAllFlashCards(){
     return allFlashCards;
    }
   /* List<FlashCard> findFlashCard(String name){
     return foundFlashCard;
    }

    public void insert(FlashCard flashCard) {
        FCRepository.insertFlashCard(flashCard);
    }*/

    public void delete(FlashCard flashCard){
       FCRepository.deleteFlashCard(flashCard);
    }


    MutableLiveData<List<FlashCard>> getSearchResults() {
    return searchResults;
}

    public void deleteAll() {
        FCRepository.deleteAllFlashCards();
    }


}
