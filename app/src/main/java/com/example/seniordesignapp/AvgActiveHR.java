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

public class AvgActiveHR extends AppCompatActivity {

    private Button backtomenu;
    private Button toresting;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    AvgActiveHRClass avgActiveHRClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avg_active_hr);

        // Letting the Buttons Move Between Screens
        backtomenu = findViewById(R.id.hrbacktomenu);
        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AvgActiveHR.this, HeartRateMenu.class);
                startActivity(i);
            }
        });

        toresting = findViewById(R.id.toresting);
        toresting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AvgActiveHR.this, AvgRestHR.class);
                startActivity(i);
            }
        });

        avgActiveHRClass = new AvgActiveHRClass();
        listView = findViewById(R.id.hractivelistView);


        database = FirebaseDatabase.getInstance();
        ref = database.getReference("AverageActiveHR");
        list = new ArrayList<>();


        adapter = new ArrayAdapter<String>(this, R.layout.hrba_list_item, R.id.hrbadateView, list);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    avgActiveHRClass = ds.getValue(AvgActiveHRClass.class);


                    list.add(avgActiveHRClass.getDate() + "   " + avgActiveHRClass.getDay() + "            " + avgActiveHRClass.getHR().toString());

                }
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
