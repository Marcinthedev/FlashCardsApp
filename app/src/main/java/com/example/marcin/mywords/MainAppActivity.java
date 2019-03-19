package com.example.marcin.mywords;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcin.mywords.ApiClassesResponse.Example;
import com.example.marcin.mywords.Utils.BottomNavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainAppActivity extends AppCompatActivity implements View.OnClickListener {
    private String definition;
    private TextView Result;
    private EditText Input;
    private Button Find;
    private Button Save;
    private ApiClient client;

    private FlashCardViewModel mflashCardViewModel;
    private static final  int ACTIVITY_NUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetupButtons();
        setupBottomNavView();
        mflashCardViewModel=ViewModelProviders.of(this).get(FlashCardViewModel.class);

        client = ServiceApi.createServiceApi();

        if(savedInstanceState!=null){
            Result.setText(savedInstanceState.getString("Definition"));
        }

    }
    private void SetupButtons(){
        Find = findViewById(R.id.button);
        Find.setOnClickListener(this);

        Save = findViewById(R.id.button2);
        Save.setOnClickListener(this);

        Input=findViewById(R.id.Text);

        Result=findViewById(R.id.Result);
    }
    //zapytanie do API - Retrofit
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                String word= Input.getText().toString();
                Call<Example> exampleCall= client.GetExampleFor(word);
                exampleCall.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> exampleCall, Response<Example> exampleResponse) {
                        if(exampleResponse.isSuccessful()){
                            Example definitionBody = exampleResponse.body();
                            //pobieranie definicji
                            definition = definitionBody.getResults().get(0).getLexicalEntries()
                                    .get(0).getEntries().get(0).getSenses().get(0).getDefinitions().get(0);
                            Result.setText(definition);
                        }
                        else {
                            System.out.println(exampleResponse.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> exampleCall, Throwable t) {
                        Toast.makeText(MainAppActivity.this,"Blad odpowiedzi",Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.button2:
            //czy istnieje ta
                    if(mflashCardViewModel.findFlashCard(Input.getText().toString()).size()==0 && !Result.getText().toString().equals("Definition")
                            && !Input.getText().toString().equals("")){
                        FlashCard flashCard= new FlashCard();
                    flashCard.setWordDb(Input.getText().toString());
                    flashCard.setDefinitionDb(Result.getText().toString());
                    mflashCardViewModel.insert(flashCard);
                    Intent i = new Intent(this,MainActivity.class);
                    startActivity(i);
                }
                //Jesli juz slowko istnieje to w odpowiedzi zawsze bedzie wiecej niz jeden element
                else if(mflashCardViewModel.findFlashCard(Input.getText().toString()).size()!=0 && !Input.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Word is already in database",Toast.LENGTH_LONG);
                    toast.show();
                }
                //konieczna definicja do zapisania
                else if(Result.getText().toString().equals("Definition") && !Input.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Need Definition Result",Toast.LENGTH_LONG);
                    toast.show();
                }
                    else if(!Result.getText().toString().equals("Definition") && Input.getText().toString().equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Need Input",Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else if(Result.getText().toString().equals("Definition") && Input.getText().toString().equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Need Input & Definition",Toast.LENGTH_LONG);
                        toast.show();
                    }

                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Definition",Result.getText().toString());

    }
    //poprawienie nav bara - biblioteka z gita
    private void setupBottomNavView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavViewHelper.setupBottomNavView(bottomNavigationViewEx);
        BottomNavViewHelper.enableNavigation(getApplicationContext(),bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}