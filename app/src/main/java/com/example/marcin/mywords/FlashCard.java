package com.example.marcin.mywords;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "FlashCard_Bank")
public class FlashCard {

    @PrimaryKey(autoGenerate = true)
    private int FcId;

    @ColumnInfo(name = "Word")
    private String WordDb;

    @ColumnInfo(name = "Definition")
    private String DefinitionDb;

    //constructor
    public FlashCard(int fcId, String wordDb, String definitionDb) {
        this.FcId = fcId;
        this.WordDb = wordDb;
        this.DefinitionDb = definitionDb;
    }

    public FlashCard() {
    }


    //Getters n setters
  //  @NonNull
    public int getFcId() {
        return this.FcId;
    }

    public void setFcId(int fcId) {
        this.FcId = fcId;
    }

    public String getWordDb() {
        return this.WordDb;
    }

    public void setWordDb(String wordDb) {
        this.WordDb = wordDb;
    }

    public String getDefinitionDb() {
        return this.DefinitionDb;
    }

    public void setDefinitionDb(String definitionDb) {
        this.DefinitionDb = definitionDb;
    }

    //Methods
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlashCard)) return false;

        FlashCard flashCard = (FlashCard) o;

        if (FcId != FlashCard.) return false;
        return title != null ? title.equals(note.title) : note.title == null;
    }*/



    @Override
    public int hashCode() {
        int result = FcId;
        result = 31 * result + (WordDb != null ? WordDb.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Words{" +
                "Word=" + FcId +
                ", Word='" + WordDb + '\'' +
                ", Definition='" + DefinitionDb + '\'' +
                '}';
    }

}
