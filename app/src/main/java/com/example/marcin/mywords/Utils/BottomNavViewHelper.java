package com.example.marcin.mywords.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.marcin.mywords.FlashCardActivityGame;
import com.example.marcin.mywords.MainActivity;
import com.example.marcin.mywords.MainAppActivity;
import com.example.marcin.mywords.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavViewHelper {

    public static void setupBottomNavView(BottomNavigationViewEx bottomNavigationViewEx){
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx viewEx){
        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent1 = new Intent(context, MainAppActivity.class); //ACTIVITY 0
                        context.startActivity(intent1);
                        break;
                    case R.id.ic_list:
                        Intent intent2 = new Intent(context, MainActivity.class);//ACTIVITY 1
                        context.startActivity(intent2);
                        break;
                    case R.id.ic_word:
                        Intent intent3 = new Intent(context, FlashCardActivityGame.class);//ACTIVITY 2
                        context.startActivity(intent3);
                        break;
                }
                return false;
            }
        });
    }
}
