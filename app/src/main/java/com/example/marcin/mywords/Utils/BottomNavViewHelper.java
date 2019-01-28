package com.example.marcin.mywords.Utils;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavViewHelper {

    public static void setupBottomNavView(BottomNavigationViewEx bottomNavigationViewEx){
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }
}
