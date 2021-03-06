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

    @Query("DELETE FROM FlashCard_Bank")
    void deleteAllFlashCards();

    //jesli jest to samo id to zmieniamy na nowe
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FlashCard flashCard);

    @Delete
    void delete(FlashCard flashCard);




}
