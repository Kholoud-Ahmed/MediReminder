package com.iti.www.medireminder.activities;

import android.app.Activity;
import android.os.Bundle;

import com.iti.www.medireminder.R;

public class MainActivity extends Activity {

    private static String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}