package com.example.vasa_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class  MainActivity extends AppCompatActivity {
    Timer time;
    Animation ani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent inte = new Intent(MainActivity.this, ac.class);
                startActivity(inte);
                finish();
            }
        }, 5000);




    }
}




