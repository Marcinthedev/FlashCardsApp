package com.example.marcin.mywords;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcin.mywords.Utils.BottomNavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.widget.RecyclerView.VERTICAL;

//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public class MainActivity extends AppCompatActivity implements View.OnClickListener{
  private String definition;
    private TextView Result;
    private EditText Input;
    private Button Find;
    private Button Save;

    //private FlashCard flashCard;
    private ApiClient client;
    private AppDatabase db;
    private FlashCardViewModel decflashCardViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act_tutorial);
setupBottomNavView();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);



        final FlashCardAdapter adapter = new FlashCardAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        android.support.v7.widget.DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        recyclerView.addItemDecoration(decoration);
        decflashCardViewModel=ViewModelProviders.of(this).get(FlashCardViewModel.class);

        decflashCardViewModel.getAllFlashCards().observe(this, new Observer<List<FlashCard>>() {
            @Override
            public void onChanged(@Nullable List<FlashCard> flashCards) {
                adapter.setFlashCards(flashCards);

                //adding new words
                FloatingActionButton fab = findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, MainAppActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });




    }

    private void SetupButtons() {
        Find = findViewById(R.id.button);
        Find.setOnClickListener(this);

        Save = findViewById(R.id.button2);
        Save.setOnClickListener(this);

        Input=findViewById(R.id.Text);

        Result=findViewById(R.id.Result);


    }
  @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:


                break;
            case R.id.button2:

                //worker thread to insert data



        }
    }

    /**
     * BottomNavigationSetup
     */
    private void setupBottomNavView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavViewHelper.setupBottomNavView(bottomNavigationViewEx);
    }


}
