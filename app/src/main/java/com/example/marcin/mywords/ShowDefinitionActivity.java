package com.example.marcin.mywords;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ShowDefinitionActivity extends AppCompatActivity {

    private static final String TAG = "ShowDefinition";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordfragment);
        Log.d(TAG, "onCreate : started");
        getIncomingIntent();

    }
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("Word") && getIntent().hasExtra("Definition")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String Word = getIntent().getStringExtra("Word");
            String Definition= getIntent().getStringExtra("Definition");

            setFlashCard(Word, Definition);
        }
    }
    private void setFlashCard(String Word, String Definition){
        Log.d(TAG, "setImage: setting te image and name to widgets.");

        TextView word = findViewById(R.id.wordout);
        word.setText(Word);

        TextView definition = findViewById(R.id.definition);
        definition.setText(Definition);

    }}
