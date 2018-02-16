package com.example.kevin.catch_my_beer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer myTimer = null; // déclaration
    private long time = 2000; // sinon = 0 par défaut

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTimer = new Timer(); // instance de classe

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                // TODO : lancer HomeActivity
                Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                //myIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(myIntent); // démarrage
                //finish(); // ou noHistory="true" dans le manifest

            }
        }, time); // time = 2000 = durée d'attente


    }
}