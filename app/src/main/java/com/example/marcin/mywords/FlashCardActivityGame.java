package com.example.marcin.mywords;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcin.mywords.Utils.BottomNavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.List;
import java.util.Random;

public class FlashCardActivityGame extends AppCompatActivity implements View.OnClickListener {
    private static final  int ACTIVITY_NUM = 2;
    private Button Check;
    private Button Next;
    private TextView definition;
    private EditText word;
    private AppDatabase db;
    private int losuj;
    private int ilosc;
    private  List<FlashCard> allFlashCards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcardgamelayout);
        setupBottomNavView();
        SetupButtons();

        db=AppDatabase.getDatabase(this);
         allFlashCards = db.flashCardDao().getAll();
         ilosc = allFlashCards.size();
        Log.i("Game","Your list size is = "+ilosc);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button3:
                if(ilosc == 0 ){
                    Toast toast = Toast.makeText(getApplicationContext(), "Brak danych",Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    Log.i("Game","Your list size is = "+ilosc);

                    losuj = (int) (Math.random()*ilosc);
                    Log.i("Game","Your random = "+losuj);

                    definition.setText(allFlashCards.get(losuj).getDefinitionDb().toString());
                }

                break;
            case R.id.button4:
                  if(word.getText().toString().equals(allFlashCards.get(losuj).getWordDb()) && !definition.getText().equals("Definition")){
                Toast toast = Toast.makeText(getApplicationContext(), "Brawo! Odpowiedz poprawna",Toast.LENGTH_LONG);
                toast.show();
            }
            else if(!word.getText().toString().equals(allFlashCards.get(losuj).getWordDb()) && !definition.getText().equals("Definition")) {
                      Toast toast = Toast.makeText(getApplicationContext(), "Blad! Postaraj sie bardziej",Toast.LENGTH_LONG);
                      toast.show();
                  }
                  else if(definition.getText().equals("Definition")){
                      Toast toast = Toast.makeText(getApplicationContext(), "Najpierw wylosuj slowo",Toast.LENGTH_LONG);
                      toast.show();
                  }
                break;
        }}








    private void SetupButtons(){
        Check = findViewById(R.id.button4);
        Check.setOnClickListener(this);

        Next = findViewById(R.id.button3);
        Next.setOnClickListener(this);

        word = findViewById(R.id.wordout);
        definition = findViewById(R.id.definition);

    }




    private void setupBottomNavView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavViewHelper.setupBottomNavView(bottomNavigationViewEx);
        BottomNavViewHelper.enableNavigation(getApplicationContext(),bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
