package com.example.marcin.mywords;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetupButtons();
        setupBottomNavView();

         client = ServiceApi.createServiceApi();
         db=AppDatabase.getDatabase(this);

    }
private void SetupButtons(){
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
                String word= Input.getText().toString();
                Call<Example> exampleCall= client.GetExampleFor(word);
                exampleCall.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> exampleCall, Response<Example> exampleResponse) {
                        if(exampleResponse.isSuccessful()){
                            Example definitionBody = exampleResponse.body();
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
                        Toast.makeText(MainAppActivity.this,"nie pyklo",Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.button2:
//jak sprawdzac czy usuniete?
 if(db.flashCardDao().findFlashCard(Input.getText().toString())!=null){
     FlashCard flashCard= new FlashCard();
     flashCard.setWordDb(Input.getText().toString());
     flashCard.setDefinitionDb(Result.getText().toString());
     db.flashCardDao().insert(flashCard);
     Intent i = new Intent(this,MainActivity.class);
     startActivity(i);
 }
 else {
     Toast toast = Toast.makeText(getApplicationContext(), "Word is already in database",Toast.LENGTH_LONG);
     toast.show();
 }



break;


        }
    }
  /*  private static class InsertDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        InsertDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }
    }*/
  private void setupBottomNavView(){
      BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
      BottomNavViewHelper.setupBottomNavView(bottomNavigationViewEx);
  }
}