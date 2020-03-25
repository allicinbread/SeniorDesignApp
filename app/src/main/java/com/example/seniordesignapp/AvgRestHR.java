package com.example.seniordesignapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AvgRestHR extends AppCompatActivity {

    private Button backtomenu;
    private Button toactive;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    AvgRestingHRClass avgRestingHRClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avg_rest_hr);

        // Letting the Buttons Move Between Screens
        backtomenu = findViewById(R.id.hrbacktomenu);
        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AvgRestHR.this, HeartRateMenu.class);
                startActivity(i);
            }
        });

        toactive = findViewById(R.id.toactive);
        toactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AvgRestHR.this, AvgActiveHR.class);
                startActivity(i);
            }
        });

        avgRestingHRClass = new AvgRestingHRClass();
        listView = findViewById(R.id.hrrestinglistView);


        database = FirebaseDatabase.getInstance();
        ref = database.getReference("AverageRestingHR");
        list = new ArrayList<>();


        adapter = new ArrayAdapter<String>(this, R.layout.hrba_list_item, R.id.hrbadateView, list);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    avgRestingHRClass = ds.getValue(AvgRestingHRClass.class);


                    list.add(avgRestingHRClass.getDate() + "   " + avgRestingHRClass.getDay() + "            " + avgRestingHRClass.getHR().toString());

                }
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
