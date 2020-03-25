package com.example.seniordesignapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HeartRateMenu extends AppCompatActivity {

    private Button backtomain;
    private Button toviewhr;
    private Button toavgresthr;
    private Button toavgacthr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_menu);

        // Letting the Buttons Move Between Screens
        backtomain = findViewById(R.id.backtomain);
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeartRateMenu.this, MainActivity.class);
                startActivity(i);
            }
        });

        toviewhr = findViewById(R.id.hrbyactivitybutton);
        toviewhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeartRateMenu.this, ViewHeartRate.class);
                startActivity(i);
            }
        });

        toavgresthr = findViewById(R.id.avgrestingbutton);
        toavgresthr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeartRateMenu.this, AvgRestHR.class);
                startActivity(i);
            }
        });

        toavgacthr = findViewById(R.id.avgactivebutton);
        toavgacthr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeartRateMenu.this, AvgActiveHR.class);
                startActivity(i);
            }
        });

    }
}
