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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public class MainActivity extends AppCompatActivity {

    private static final  int ACTIVITY_NUM = 1;

    private FlashCardViewModel decflashCardViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act_tutorial);
        setupBottomNavView();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);



        final FlashCardAdapter adapter = new FlashCardAdapter(this);
        recyclerView.setAdapter(adapter);
        //Layout do recyclera
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
        //ItemTouchHelper zeby poprzez przesuniecie usuwac dane slowko z bazy danych
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                decflashCardViewModel.delete(adapter.getFlashCardAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT);
            }
        }).attachToRecyclerView(recyclerView);




    }


    /**
     * BottomNavigationSetup
     */
    private void setupBottomNavView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavViewHelper.setupBottomNavView(bottomNavigationViewEx);
        BottomNavViewHelper.enableNavigation(getApplicationContext(),bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_flashcards:
                decflashCardViewModel.deleteAll();
                Toast.makeText(this,"All FlashCards Deleted", Toast.LENGTH_SHORT).show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }

    }
}
