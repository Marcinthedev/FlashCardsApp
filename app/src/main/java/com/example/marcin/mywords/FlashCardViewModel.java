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

    public void insert(FlashCard flashCard) {
        FCRepository.insertFlashCard(flashCard);
    }

    MutableLiveData<List<FlashCard>> getSearchResults() {
    return searchResults;
}





 /*   public void findProduct(String name) {
        FCRepository.findProduct(name);
    }

    public void deleteFlashCard(String name) {
        FCRepository.deleteFlashCard(name);
    }*/
}
