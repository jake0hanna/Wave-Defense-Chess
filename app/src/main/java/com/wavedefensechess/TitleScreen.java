package com.wavedefensechess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TitleScreen extends AppCompatActivity {

    int fuck = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(fuck);
    }
}