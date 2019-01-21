package com.example.marcin.mywords;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface FlashCardDao {
    @Query("SELECT * FROM FlashCard_Bank")
    List<FlashCard> getAll();

    @Query("SELECT * FROM FlashCard_Bank WHERE Word= :name")
    List<FlashCard> findFlashCard(String name);

    @Query("SELECT * from FlashCard_Bank ORDER BY Word ASC")
    LiveData<List<FlashCard>> getAllFlashCards();
//?
    @Query("SELECT * FROM FlashCard_Bank WHERE FcId IN (:FlashCardId)")
    LiveData<FlashCard> loadAllByIds(String FlashCardId);

    @Query("SELECT * FROM FlashCard_Bank WHERE Word LIKE :WordOut AND " +
            "Definition LIKE :DefinitionOut LIMIT 1")
    FlashCard findByName(String WordOut, String DefinitionOut);

    //jesli jest to samo id to zmieniamy na nowe
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FlashCard flashCard);

    /*@Insert
    void insertAll(FlashCard... flashCards);*/

    @Delete
    void delete(FlashCard flashCard);
    @Delete
    public void deleteFCs(FlashCard... flashCards);

//    ----------------------------------------
    @Update
    void updateFlashcards(FlashCard...flashCards);



}
